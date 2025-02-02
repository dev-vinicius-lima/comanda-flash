package com.vinicius_lima.comanda_flash.dto;

import com.vinicius_lima.comanda_flash.entities.CustomerOrder;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class CustomerInsertOrderDTO {
    private Long idOrder;
    private Integer tableNumber;
    private Long customerId;
    private String customerName;
    private String status = "Aberto";
    private String createdAt;

    public CustomerInsertOrderDTO() {
    }

    public CustomerInsertOrderDTO(CustomerOrder order) {
        idOrder = order.getId();
        tableNumber = order.getTable().getNumber();
        customerId = order.getCustomer().getId();
        customerName = order.getCustomer().getName();
        status = order.getStatus();
        createdAt = formatDate(order.getCreatedAt());

    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long id) {
        this.idOrder = id;
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
}