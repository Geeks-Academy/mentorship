package com.programmersonly.mentorship.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class BusinessException extends RuntimeException implements ErrorResponse {

    private ErrorCode errorCode;
    private String message;

    public BusinessException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = errorCode.getValue();
    }

    @Override
    public ErrorCode getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
