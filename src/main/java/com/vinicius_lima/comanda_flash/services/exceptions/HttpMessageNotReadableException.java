package com.vinicius_lima.comanda_flash.services.exceptions;

public class HttpMessageNotReadableException extends RuntimeException {
    public HttpMessageNotReadableException(String message) {
        super(message);
    }
}
