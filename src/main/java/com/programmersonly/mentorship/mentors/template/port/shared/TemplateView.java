package com.programmersonly.mentorship.mentors.template.port.shared;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class TemplateView {
    private UUID userId;
    private String fullName;
}
