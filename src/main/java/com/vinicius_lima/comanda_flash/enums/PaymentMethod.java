package com.vinicius_lima.comanda_flash.enums;

public enum PaymentMethod {
    DEBITO,
    CREDITO,
    PIX;

    public static PaymentMethod fromString(String paymentMethod) {
        for (PaymentMethod method : PaymentMethod.values()) {
            if (method.name().equalsIgnoreCase(paymentMethod)) {
                return method;
            }
        }
        throw new IllegalArgumentException("Método de pagamento inválido: " + paymentMethod);
    }


}
