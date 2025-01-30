package com.vinicius_lima.comanda_flash.dto;

import com.vinicius_lima.comanda_flash.entities.OrderItem;

public class OrderItemDTO {
    private Long id;
    private String productName;
    private int quantity;
    private final double totalPrice;

    public OrderItemDTO(OrderItem item) {
        id = item.getId();
        productName = item.getProduct().getName();
        quantity = item.getQuantity();
        totalPrice = item.getTotalPrice();
    }

    public Long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}