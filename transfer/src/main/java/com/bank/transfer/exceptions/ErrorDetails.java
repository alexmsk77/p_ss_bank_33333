package com.bank.transfer.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Класс, который описывает подробности выбрасываемых исключений.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
    private LocalDateTime localDateTime;
    private String message;
    private String path;
}
