package com.programmersonly.mentorship.commons.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BasicErrorResponse implements ErrorResponse {

    private final String errorCode;
    private final String message;

    @Override
    public String getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
