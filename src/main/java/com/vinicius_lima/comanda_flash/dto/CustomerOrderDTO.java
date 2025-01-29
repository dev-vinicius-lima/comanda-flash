package com.vinicius_lima.comanda_flash.dto;

import java.util.List;

public class CustomerOrderDTO {
    private Long id;
    private Long tableId; // ID da mesa
    private Long customerId; // ID do cliente
    private List<OrderItemDTO> items; // Lista de itens de pedido

    public CustomerOrderDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }
}