package com.bank.antifraud.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Класс для создания тела ответа при выбрасывании ошибки.
 */
@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse {

    private String message;
}
