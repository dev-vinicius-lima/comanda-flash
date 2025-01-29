package com.vinicius_lima.comanda_flash.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vinicius_lima.comanda_flash.entities.Table;

public class TableDTO {
    @JsonIgnore
    private Long id;
    private Integer number;
    private String status;

    public TableDTO() {
    }

    public TableDTO(Table entity) {
        number = entity.getNumber();
        status = entity.getStatus();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}