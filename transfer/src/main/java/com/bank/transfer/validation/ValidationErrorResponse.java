package com.bank.transfer.validation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;


/**
 * Класс, содержащий все нарушения валидации полей.
 */
@Getter
@RequiredArgsConstructor
public class ValidationErrorResponse {

    private final List<Violation> violations;
}


