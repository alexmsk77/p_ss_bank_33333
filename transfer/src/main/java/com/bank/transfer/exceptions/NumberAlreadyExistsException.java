package com.bank.transfer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Класс, созданный для обработки исключений уже существующих уникальных полей.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NumberAlreadyExistsException extends RuntimeException {
    public NumberAlreadyExistsException(String message) {
        super(message);
    }
}
