package com.vinicius_lima.comanda_flash.dto;

import com.vinicius_lima.comanda_flash.entities.OrderItem;

public class OrderItemDTO {
    private final Long productId;
    private String productName;
    private int quantity;
    private final double totalPrice;

    public OrderItemDTO(OrderItem item) {
        productId = item.getProduct().getId();
        productName = item.getProduct().getName();
        quantity = item.getQuantity();
        totalPrice = item.getTotalPrice();
    }

    public Long getProductId() {
        return productId;
    }

    public double getTotalPrice() {
        return totalPrice;
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