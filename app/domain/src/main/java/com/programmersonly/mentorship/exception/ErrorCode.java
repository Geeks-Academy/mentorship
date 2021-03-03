package com.programmersonly.mentorship.exception;

public enum ErrorCode {
    MS01("Cannot confirm"),
    MS02("Cannot find template");

    private String value;

    ErrorCode(String message) {
        this.value = message;
    }

    public String getValue() {
        return value;
    }
}
