package com.programmersonly.mentorship.mentors.service.exception;

public class TemplateAlreadyConfirmed extends RuntimeException {
  public TemplateAlreadyConfirmed(String id) {
    super(String.format("Mentor with id %s  already confirmed", id));
  }
}
