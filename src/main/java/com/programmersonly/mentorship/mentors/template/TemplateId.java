package com.programmersonly.mentorship.mentors.template;

import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
class TemplateId {
    @NonNull UUID templateId;
}
