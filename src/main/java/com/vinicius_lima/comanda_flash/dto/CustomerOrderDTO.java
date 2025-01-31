package com.vinicius_lima.comanda_flash.dto;

import com.vinicius_lima.comanda_flash.entities.CustomerOrder;
import com.vinicius_lima.comanda_flash.entities.OrderItem;


import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class CustomerOrderDTO {
    private Long id;
    private Integer tableNumber;
    private Long customerId;
    private String customerName;
    private String status = "Aberto";
    private String createdAt;
    private String updatedAt;

    private List<OrderItemDTO> items;

    private double totalValue;
    private String formattedTotalValue;


    public CustomerOrderDTO() {
    }

    public CustomerOrderDTO(CustomerOrder order) {
        id = order.getId();
        tableNumber = order.getTable().getNumber();
        customerId = order.getCustomer().getId();
        customerName = order.getCustomer().getName();
        status = order.getStatus();
        createdAt = formatDate(order.getCreatedAt());
        if (getUpdatedAt() != null) {
            updatedAt = formatDate(order.getUpdatedAt());
        } else {
            updatedAt = "";
        }
        items = order.getItems().stream().map(OrderItemDTO::new).toList();

        totalValue = order.getItems().stream()
                .mapToDouble(OrderItem::getTotalPrice)
                .sum();
        formattedTotalValue = formatCurrency(totalValue);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
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

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    private String formatDate(LocalDateTime dateTime) {
        if (dateTime != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
            return dateTime.format(formatter);
        }
        return null;
    }

    private String formatCurrency(double value) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return currencyFormatter.format(value);
    }

    public String getFormattedTotalValue() {
        return formattedTotalValue;
    }

    public void setFormattedTotalValue(String formattedTotalValue) {
        this.formattedTotalValue = formattedTotalValue;
    }
}