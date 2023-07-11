package com.bank.antifraud.handler;

import com.bank.antifraud.exception.ExceptionResponse;
import com.bank.antifraud.exception.SuspiciousTransferNotFoundException;
import com.bank.antifraud.exception.SuspiciousTransferNotValidException;
import com.bank.antifraud.exception.UniqueFieldAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Хэндлер всех ошибок.
 */
@ControllerAdvice
public class SuspiciousTransferExceptionHandler {

    @ExceptionHandler(SuspiciousTransferNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleNotValidException(SuspiciousTransferNotValidException e) {
        ExceptionResponse response = new ExceptionResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SuspiciousTransferNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(SuspiciousTransferNotFoundException e) {
        ExceptionResponse response = new ExceptionResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UniqueFieldAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleUniqueFieldException(UniqueFieldAlreadyExistsException e) {
        ExceptionResponse response = new ExceptionResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
