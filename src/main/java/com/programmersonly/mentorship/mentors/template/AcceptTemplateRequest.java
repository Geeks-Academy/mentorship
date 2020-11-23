package com.programmersonly.mentorship.mentors.template;

import lombok.Data;

import java.util.UUID;

@Data
class AcceptTemplateRequest {
    private UUID templateId;
}
