package com.retaildiscounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DiscountCalculatorExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<DiscountCalculatorErrorResponse> handleException(EntityNotFoundException exc) {
        DiscountCalculatorErrorResponse error = new DiscountCalculatorErrorResponse();
        error.setErrorCode(HttpStatus.NOT_FOUND.value());
        error.setErrorMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<DiscountCalculatorErrorResponse> handleException(Exception exc) {
        DiscountCalculatorErrorResponse error = new DiscountCalculatorErrorResponse();
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setErrorMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
