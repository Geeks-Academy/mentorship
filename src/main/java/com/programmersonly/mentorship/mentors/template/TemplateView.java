package com.programmersonly.mentorship.mentors.template;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
class TemplateView {
    private UUID userId;
    private String fullName;
}
