package com.vinicius_lima.comanda_flash.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "tb_table")
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Integer number;
    private String status;

    @OneToMany(mappedBy = "table", cascade = CascadeType.ALL)
    private List<CustomerOrder> orders = new ArrayList<>();

    public Table() {
    }

    public Table(Integer number, String status, List<CustomerOrder> orders) {
        this.number = number;
        this.status = status;
        this.orders = orders;
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

    public List<CustomerOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<CustomerOrder> orders) {
        this.orders = orders;
    }
}
