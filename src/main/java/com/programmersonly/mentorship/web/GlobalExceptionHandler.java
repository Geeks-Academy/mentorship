package com.programmersonly.mentorship.web;

import com.programmersonly.mentorship.commons.exception.BusinessException;
import com.programmersonly.mentorship.commons.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {BusinessException.class})
    protected ResponseEntity<?> handleBusinessException(BusinessException ex) {
        return ex.getResponse();
    }

    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<?> handleBusinessException(NotFoundException ex) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
