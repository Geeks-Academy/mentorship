package com.programmersonly.mentorship.mentors.service.exception;

public class TemplateNotFound extends RuntimeException {
    public TemplateNotFound(String id) {
      super(String.format("Template with id %s not found", id));
    }
}
