package com.programmersonly.mentorship.exception;

public class ConfirmationConflictException extends BusinessException {

    public ConfirmationConflictException() {
        super(ErrorCode.MS01);
    }
}
