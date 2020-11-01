package com.programmersonly.mentorship.mentors.mentorsTemplate.domain;

import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
public class MentorTemplateId {
    @NonNull UUID mentorTemplateId;
}
