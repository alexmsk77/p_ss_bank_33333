package com.bank.antifraud.exception;

/**
 * Ошибка при ненахождении искомой сущности в БД.
 */
public class SuspiciousTransferNotFoundException extends RuntimeException{

    public SuspiciousTransferNotFoundException(String message) {
        super(message);
    }
}
