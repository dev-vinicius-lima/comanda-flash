package com.vinicius_lima.comanda_flash.services;

public interface StockService {
    void subtractStock(Long productId, Integer quantity);

    void addStock(Long productId, Integer quantity);

    boolean checkLowStock(Long productId);
}
