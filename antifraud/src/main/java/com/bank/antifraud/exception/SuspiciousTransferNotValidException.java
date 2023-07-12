package com.bank.antifraud.exception;

/**
 * Ошибка при валидации сущностей.
 */
public class SuspiciousTransferNotValidException extends RuntimeException{

    public SuspiciousTransferNotValidException(String message) {
        super(message);
    }
}
