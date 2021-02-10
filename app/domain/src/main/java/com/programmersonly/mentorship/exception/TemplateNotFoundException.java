package com.programmersonly.mentorship.exception;

public class TemplateNotFoundException extends BusinessException {

    public TemplateNotFoundException() {
        super(ErrorCode.MS02);
    }
}
