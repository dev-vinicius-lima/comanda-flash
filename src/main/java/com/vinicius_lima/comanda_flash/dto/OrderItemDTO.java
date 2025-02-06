package com.vinicius_lima.comanda_flash.dto;

import com.vinicius_lima.comanda_flash.entities.OrderItem;

import java.math.BigDecimal;

public class OrderItemDTO {
    private Long productId;
    private String productName;
    private int quantity;
    private BigDecimal totalPrice;
    private BigDecimal unitPrice;

    public OrderItemDTO() {
    }

    public OrderItemDTO(OrderItem item) {
        productId = item.getProduct().getId();
        productName = item.getProduct().getName();
        quantity = item.getQuantity();
        this.totalPrice = BigDecimal.valueOf(item.getTotalPrice());
        this.unitPrice = BigDecimal.valueOf(item.getProduct().getUnitPrice());
    }

    public Long getProductId() {
        return productId;
    }

    public BigDecimal getTotalPrice() {
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

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}