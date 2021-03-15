package com.geeksacagemy.mentorship.direct.web;

import com.geeksacagemy.mentorship.exception.BusinessException;
import com.geeksacagemy.mentorship.exception.ConfirmationConflictException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {BusinessException.class})
    protected ResponseEntity<BusinessException> handleBusinessException(BusinessException exception) {
        if (exception instanceof ConfirmationConflictException){
            return new ResponseEntity<>(exception, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

}
