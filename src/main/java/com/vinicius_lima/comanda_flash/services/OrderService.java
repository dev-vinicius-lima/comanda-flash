package com.vinicius_lima.comanda_flash.services;

import com.vinicius_lima.comanda_flash.dto.ClosedOrderDTO;
import com.vinicius_lima.comanda_flash.dto.CustomerDTO;
import com.vinicius_lima.comanda_flash.dto.CustomerOrderDTO;
import com.vinicius_lima.comanda_flash.dto.OrderItemDTO;
import com.vinicius_lima.comanda_flash.entities.*;
import com.vinicius_lima.comanda_flash.repositories.CustomerRepository;
import com.vinicius_lima.comanda_flash.repositories.OrderRepository;
import com.vinicius_lima.comanda_flash.repositories.ProductRepository;
import com.vinicius_lima.comanda_flash.repositories.TableRepository;
import com.vinicius_lima.comanda_flash.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
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

    public CustomerOrderDTO openOrder(Integer tableNumber, CustomerDTO customerDTO) {
        Table table = (Table) tableRepository.findByNumber(tableNumber).orElseThrow(() -> new RuntimeException("Table not found"));

        Customer customer = customerRepository.findByName(customerDTO.getName()).orElseGet(() -> {
            Customer newCustomer = convertToEntity(customerDTO);
            return customerRepository.save(newCustomer);
        });

        CustomerOrder order = new CustomerOrder(table, customer, new ArrayList<>());
        order = orderRepository.save(order);
        return convertToDTO(order);
    }

    public CustomerOrderDTO addProductToOrder(Long orderId, Long productId, Integer quantity) {

        CustomerOrder order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setQuantity(quantity);

        order.getItems().add(orderItem);
        orderRepository.save(order);

        BigDecimal totalValue = order.getItems().stream()
                .map(item -> BigDecimal.valueOf(item.getTotalPrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
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


        return new CustomerOrderDTO(order);
    }

    private Customer convertToEntity(CustomerDTO customerDTO) {
        return new Customer(customerDTO.getName(), customerDTO.isAnonymous());
    }

    public CustomerOrderDTO findById(Long orderId) {
        return convertToDTO(orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found")));
    }

}
