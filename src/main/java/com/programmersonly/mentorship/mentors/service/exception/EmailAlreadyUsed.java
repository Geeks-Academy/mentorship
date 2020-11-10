package com.programmersonly.mentorship.mentors.service.exception;

public class EmailAlreadyUsed extends RuntimeException {
    public EmailAlreadyUsed(String email) {
      super(String.format("Template for given email %s already exists", email));
    }
}
