package com.programmersonly.mentorship.mentors.mentorsTemplate.application;

import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
public class CreateMentorCommand {
    @NonNull String description;
    @NonNull UUID userId;
}
