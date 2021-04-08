package com.programmersonly.mentorship.exception;

public enum ErrorCode {
    MS01("Cannot confirm"),
    MS02("Cannot find template"),



    MS11 ("Cannot find offer"),
    MS12("Offer do not welcome attender anymore"),
    MS13("Can not confirm attender"),
    MS14("Attender must be in attender set"),
    MS15("Offer in current state can not be canceled"),
    MS16("Offer can be canceled only by mentor or attender"),
    MS17("Only offers finished and ungraded offers can be graded."),
    MS18("Only the one that attended the consultation can grade it"),
    MS19("Attender not found");
    private String value;

    ErrorCode(String message) {
        this.value = message;
    }

    public String getValue() {
        return value;
    }
}
