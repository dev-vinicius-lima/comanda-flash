package com.vinicius_lima.comanda_flash.dto;

public class ClosedOrderDTO {
    private Long id;
    private Integer tableNumber;
    private Long customerId;
    private String customerName;
    private String status;
    private Double totalValue;
    private String consumptionDescription;

    public ClosedOrderDTO() {
    }

    public ClosedOrderDTO(Long id, Integer tableNumber, Long customerId, String customerName, String status, Double totalValue, String consumptionDescription) {
        this.id = id;
        this.tableNumber = tableNumber;
        this.customerId = customerId;
        this.customerName = customerName;
        this.status = status;
        this.totalValue = totalValue;
        this.consumptionDescription = consumptionDescription;
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

    public String getConsumptionDescription() {
        return consumptionDescription;
    }

    public void setConsumptionDescription(String consumptionDescription) {
        this.consumptionDescription = consumptionDescription;
    }


}
