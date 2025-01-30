package com.vinicius_lima.comanda_flash.services;

import com.vinicius_lima.comanda_flash.dto.ClosedOrderDTO;
import com.vinicius_lima.comanda_flash.dto.CustomerDTO;
import com.vinicius_lima.comanda_flash.dto.CustomerOrderDTO;
import com.vinicius_lima.comanda_flash.entities.Customer;
import com.vinicius_lima.comanda_flash.entities.CustomerOrder;
import com.vinicius_lima.comanda_flash.entities.OrderItem;
import com.vinicius_lima.comanda_flash.entities.Table;
import com.vinicius_lima.comanda_flash.repositories.CustomerRepository;
import com.vinicius_lima.comanda_flash.repositories.OrderRepository;
import com.vinicius_lima.comanda_flash.repositories.TableRepository;
import com.vinicius_lima.comanda_flash.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private TableRepository tableRepository;

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

    public ClosedOrderDTO closeOrder(Long orderId) {
        CustomerOrder order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        order.setStatus("Fechada");
        orderRepository.save(order);

        double totalValue = order.getItems().stream()
                .mapToDouble(OrderItem::getTotalPrice)
                .sum();

        String consumptionDescription = order.getItems().stream()
                .map(item -> item.getQuantity() + " x " + item.getProduct().getName())
                .collect(Collectors.joining(", "));

        return new ClosedOrderDTO(
                order.getId(),
                order.getTable().getNumber(),
                order.getCustomer().getId(),
                order.getCustomer().getName(),
                order.getStatus(),
                totalValue,
                consumptionDescription
        );
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
}
