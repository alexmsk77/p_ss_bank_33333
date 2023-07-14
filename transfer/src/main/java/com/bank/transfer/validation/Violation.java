package com.bank.transfer.validation;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Класс, созданный для описания подробностей нарушений валидации полей.
 */
@Getter
@RequiredArgsConstructor
public class Violation {
        private final String fieldName;
        private final String message;
}
