package com.programmersonly.mentorship.exception;

public class AttenderNotInRequestSetException extends BusinessException {
    public AttenderNotInRequestSetException() {
        super(ErrorCode.MS14);
    }
}
