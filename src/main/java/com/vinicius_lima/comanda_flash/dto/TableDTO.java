package com.vinicius_lima.comanda_flash.dto;

import com.vinicius_lima.comanda_flash.entities.Table;
import com.vinicius_lima.comanda_flash.enums.StatusText;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;


public class TableDTO {
    private Long id;

    @NotNull(message = "O número da mesa é obrigatório.")
    private Integer number;

    @Enumerated(EnumType.STRING)
    private StatusText status = StatusText.LOCAL;

    private List<CustomerOrderDTO> orders = new ArrayList<>();


    public TableDTO() {
    }

    public TableDTO(Table entity) {
        id = entity.getId();
        number = entity.getNumber();
        status = StatusText.valueOf(entity.getStatus());
        orders = entity.getOrders().stream().map(CustomerOrderDTO::new).toList();
    }

    public TableDTO(TableDTO entity) {
        id = entity.getId();
        number = entity.getNumber();
        status = entity.getStatus();
        orders = entity.getOrders();
    }


    public Long getId() {
        return id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public StatusText getStatus() {
        return status;
    }

    public void setStatus(StatusText status) {
        this.status = status;
    }

    public List<CustomerOrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<CustomerOrderDTO> orders) {
        this.orders = orders;
    }
}