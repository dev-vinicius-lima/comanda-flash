package com.vinicius_lima.comanda_flash.dto;

public class StockStatusDTO {
    private Integer currentStock;
    private Integer threshold;
    private Boolean isLowStock;

    public StockStatusDTO() {
    }

    public StockStatusDTO(Integer currentStock, Integer threshold, Boolean isLowStock) {
        this.currentStock = currentStock;
        this.threshold = threshold;
        this.isLowStock = isLowStock;
    }

    public Integer getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(Integer currentStock) {
        this.currentStock = currentStock;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public Boolean getLowStock() {
        return isLowStock;
    }

    public void setLowStock(Boolean lowStock) {
        isLowStock = lowStock;
    }
}
