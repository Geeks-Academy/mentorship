package com.programmersonly.mentorship.commons.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BusinessException extends RuntimeException {
    private ResponseEntity<? extends ErrorResponse> response;

    public BusinessException(HttpStatus status, String message) {
    }
}
