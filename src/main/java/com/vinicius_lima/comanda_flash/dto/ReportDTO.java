package com.vinicius_lima.comanda_flash.dto;

import java.util.Date;
import java.util.List;

public class ReportDTO {
    private Long id;
    private Date startDate;
    private Date endDate;
    private List<Long> sales;

    public ReportDTO() {
    }

    public Long getId() {
        return id;
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

    public List<Long> getSales() {
        return sales;
    }

    public void setSales(List<Long> sales) {
        this.sales = sales;
    }
}