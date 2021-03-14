package com.programmersonly.mentorship.exception;

public class CannotFindOfferException extends BusinessException {

    public CannotFindOfferException() {
        super(ErrorCode.MS11);
    }
}
