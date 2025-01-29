package com.vinicius_lima.comanda_flash.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ResponseMessageHttpDTO {
    private Long customerId;
    private String message;
    private String timestamp;

    public ResponseMessageHttpDTO(Long customerId, String message) {
        this.customerId = customerId;
        this.message = message;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }
}