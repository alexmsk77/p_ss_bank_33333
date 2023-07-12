package com.bank.antifraud.exception;

/**
 * Ошибка при добавлении/измененни сущности в БД, когда совпадают уникальные поля.
 */
public class UniqueFieldAlreadyExistsException extends RuntimeException{

    public UniqueFieldAlreadyExistsException(String message) {
        super(message);
    }
}
