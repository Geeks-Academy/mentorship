package com.programmersonly.mentorship.reservations.mentorsTemplate.domain;

import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
public class MentorTemplateId {
    @NonNull UUID mentorTemplateId;
}
