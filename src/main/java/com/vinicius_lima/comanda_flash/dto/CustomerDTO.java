package com.vinicius_lima.comanda_flash.dto;

import com.vinicius_lima.comanda_flash.entities.Customer;


public class CustomerDTO {
    private Long id;
    private String name;
    private boolean isAnonymous;

    public CustomerDTO() {
    }

    public CustomerDTO(Customer customer) {
        id = customer.getId();
        name = customer.getName();
        isAnonymous = customer.isAnonymous();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean isAnonymous) {
        this.isAnonymous = isAnonymous;
    }
}