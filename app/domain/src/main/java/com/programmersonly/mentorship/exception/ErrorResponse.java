package com.programmersonly.mentorship.exception;

public interface ErrorResponse {
    ErrorCode getErrorCode();
    String getMessage();
}
