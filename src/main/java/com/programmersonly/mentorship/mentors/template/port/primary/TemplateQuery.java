package com.programmersonly.mentorship.mentors.template.port.primary;

import com.programmersonly.mentorship.mentors.template.port.shared.TemplateView;

import java.util.UUID;

public interface TemplateQuery {
    TemplateView findByTemplateId(UUID templateId);
}
