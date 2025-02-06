package com.vinicius_lima.comanda_flash.dto;

import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;
import java.util.List;

public class ClosedOrderDTO {
    private Long id;
    private Integer tableNumber;
    private Long customerId;
    private String customerName;
    private String status;
    private Double totalValue;
    private String closedAt;
    private List<OrderItemDTO> items;
    private String paymentMethod;

    public ClosedOrderDTO() {
    }

    public ClosedOrderDTO(Long id, Integer tableNumber, Long customerId, String customerName, String status, Double totalValue, String closedAt, List<OrderItemDTO> items, String paymentMethod) {
        this.id = id;
        this.tableNumber = tableNumber;
        this.customerId = customerId;
        this.customerName = customerName;
        this.status = status;
        this.totalValue = totalValue;
        this.closedAt = closedAt;
        this.items = items;
        this.paymentMethod = paymentMethod;
    }

    public Long getId() {
        return id;
    }

    public Integer getTableNumber() {
        return tableNumber;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(String closedAt) {
        this.closedAt = closedAt;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
