package com.programmersonly.mentorship.mentors.template;

import com.programmersonly.mentorship.mentors.template.TemplateView;

import java.util.UUID;

interface TemplateQuery {
    TemplateView findByTemplateId(UUID templateId);
}
