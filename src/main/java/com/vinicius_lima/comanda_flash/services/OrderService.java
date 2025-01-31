package com.vinicius_lima.comanda_flash.services;

import com.vinicius_lima.comanda_flash.dto.ClosedOrderDTO;
import com.vinicius_lima.comanda_flash.dto.CustomerDTO;
import com.vinicius_lima.comanda_flash.dto.CustomerOrderDTO;
import com.vinicius_lima.comanda_flash.entities.*;
import com.vinicius_lima.comanda_flash.repositories.*;
import com.vinicius_lima.comanda_flash.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;


    public CustomerOrderDTO openOrder(Integer tableNumber, CustomerDTO customerDTO) {

        Table table = (Table) tableRepository.findByNumber(tableNumber).orElseThrow(() -> new ResourceNotFoundException("Table not found"));

        Customer customer = customerRepository.findByName(customerDTO.getName()).orElseGet(() -> {
            Customer newCustomer = convertToEntity(customerDTO);
            return customerRepository.save(newCustomer);
        });

        CustomerOrder order = new CustomerOrder(table, customer, new ArrayList<>());
        order = orderRepository.save(order);

        table.getOrders().add(order);
        tableRepository.save(table);

        return convertToDTO(order);
    }

    public CustomerOrderDTO addProductToOrder(Long orderId, Long productId, Integer quantity) {

        CustomerOrder order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setQuantity(quantity);

        order.getItems().add(orderItem);
        orderRepository.save(order);

        BigDecimal totalValue = order.getItems().stream().map(item -> BigDecimal.valueOf(item.getTotalPrice())).reduce(BigDecimal.ZERO, BigDecimal::add);
        totalValue = totalValue.setScale(2, RoundingMode.HALF_UP);


        CustomerOrderDTO orderDTO = convertToDTO(order);
        orderDTO.setTotalValue(totalValue.doubleValue());
        return orderDTO;

    }

    public ClosedOrderDTO closeOrder(Long orderId) {
        CustomerOrder order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        order.setStatus("Fechada");
        orderRepository.save(order);

        double totalValue = order.getItems().stream().mapToDouble(OrderItem::getTotalPrice).sum();

        String consumptionDescription = order.getItems().stream().map(item -> item.getQuantity() + " x " + item.getProduct().getName()).collect(Collectors.joining(", "));

        return new ClosedOrderDTO(order.getId(), order.getTable().getNumber(), order.getCustomer().getId(), order.getCustomer().getName(), order.getStatus(), totalValue, consumptionDescription);
    }

    private CustomerOrderDTO convertToDTO(CustomerOrder order) {
        CustomerOrderDTO dto = new CustomerOrderDTO(order);
        dto.setId(order.getId());
        dto.setTableNumber(order.getTable().getNumber());
        dto.setCustomerName(order.getCustomer().getName());
        dto.setStatus(order.getTable().getStatus());
        dto.setCustomerId(order.getCustomer().getId());

        double totalValue = order.getItems().stream().mapToDouble(OrderItem::getTotalPrice).sum();
        dto.setTotalValue(totalValue);

        return new CustomerOrderDTO(order);
    }

    private Customer convertToEntity(CustomerDTO customerDTO) {
        return new Customer(customerDTO.getName(), customerDTO.isAnonymous());
    }

    public CustomerOrderDTO findById(Long orderId) {
        CustomerOrder order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        BigDecimal totalValue = order.getItems().stream().map(item -> BigDecimal.valueOf(item.getTotalPrice())).reduce(BigDecimal.ZERO, BigDecimal::add);
        totalValue = totalValue.setScale(2, RoundingMode.HALF_UP);

        CustomerOrderDTO orderDTO = convertToDTO(order);
        orderDTO.setTotalValue(totalValue.doubleValue());
        return orderDTO;
    }

    public Page<CustomerOrderDTO> findAllPaged(Pageable pageable) {
        Page<CustomerOrder> orderList = orderRepository.findAll(pageable);

        return orderList.map(order -> {
            double totalValue = order.getItems().stream().mapToDouble(OrderItem::getTotalPrice).sum();
            CustomerOrderDTO dto = convertToDTO(order);
            dto.setTotalValue(totalValue);
            return dto;
        });
    }

    @Transactional
    public void delete(Long id) {
        CustomerOrder order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        Table table = order.getTable();
        table.getOrders().remove(order);
        tableRepository.save(table);

        orderRepository.deleteById(id);
    }

    @Transactional
    public void deleteProductFromOrder(Long orderId, Long productId) {

        CustomerOrder order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        OrderItem orderItem = order.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst().orElseThrow(() -> new ResourceNotFoundException("Product not found in the order"));

        if (orderItem.getQuantity() > 1) {
            orderItem.setQuantity(orderItem.getQuantity() - 1);
        } else {
            order.getItems().remove(orderItem);
            orderItemRepository.delete(orderItem);
            orderRepository.save(order);
        }


    }
}
