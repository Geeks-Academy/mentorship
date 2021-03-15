package com.geeksacagemy.mentorship.exception;

public interface ErrorResponse {
    ErrorCode getErrorCode();
    String getMessage();
}
