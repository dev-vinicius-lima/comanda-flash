package com.vinicius_lima.comanda_flash.dto;

public class OrderItemDTO {
    private Long id;
    private Long productId; // ID do produto
    private int quantity;

    public OrderItemDTO() {
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}