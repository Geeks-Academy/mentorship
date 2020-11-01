package com.programmersonly.mentorship.mentors.template.domain;

import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
public class MentorTemplateId {
    @NonNull UUID mentorTemplateId;
}
