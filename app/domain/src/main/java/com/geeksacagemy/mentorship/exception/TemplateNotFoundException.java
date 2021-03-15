package com.geeksacagemy.mentorship.exception;

public class TemplateNotFoundException extends BusinessException {

    public TemplateNotFoundException() {
        super(ErrorCode.MS02);
    }
}
