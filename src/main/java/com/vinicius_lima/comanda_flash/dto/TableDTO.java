package com.vinicius_lima.comanda_flash.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vinicius_lima.comanda_flash.entities.Table;
import com.vinicius_lima.comanda_flash.enums.StatusText;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class TableDTO {
    @JsonIgnore
    private Long id;

    @NotNull(message = "O número da mesa é obrigatório.")
    private Integer number;
    @NotBlank(message = "O status da mesa é obrigatório.")
    private StatusText status;

    public TableDTO() {
    }

    public TableDTO(Table entity) {
        number = entity.getNumber();
        status = StatusText.valueOf(entity.getStatus());
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
}