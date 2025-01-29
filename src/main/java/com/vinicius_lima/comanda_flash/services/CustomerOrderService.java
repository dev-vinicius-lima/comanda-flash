package com.vinicius_lima.comanda_flash.services;

import com.vinicius_lima.comanda_flash.entities.CustomerOrder;
import com.vinicius_lima.comanda_flash.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerOrderService {
    @Autowired
    private OrderRepository orderRepository;

    public CustomerOrder saveOrder(CustomerOrder order) {
        return orderRepository.save(order);
    }

    public List<CustomerOrder> getAllOrders() {
        return orderRepository.findAll();
    }

    public CustomerOrder getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}