package com.vinicius_lima.comanda_flash.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity(name = "tb_report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date startDate;
    private Date endDate;

    @OneToMany
    @JoinColumn(name = "report_id")
    private List<CustomerOrder> sales;

    public Report() {
    }

    public Report(Date startDate, Date endDate, List<CustomerOrder> sales) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.sales = sales;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<CustomerOrder> getSales() {
        return sales;
    }

    public void setSales(List<CustomerOrder> sales) {
        this.sales = sales;
    }
}
