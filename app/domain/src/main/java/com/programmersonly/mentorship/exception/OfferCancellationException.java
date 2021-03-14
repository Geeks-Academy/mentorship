package com.programmersonly.mentorship.exception;


public class OfferCancellationException extends BusinessException {
    public OfferCancellationException(ErrorCode code) {
        super(code);
    }
}
