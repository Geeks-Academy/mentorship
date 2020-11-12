package com.programmersonly.mentorship.mentors.template;

import java.util.UUID;

interface TemplateQuery {
    TemplateResponse findByTemplateId(UUID templateId);
}
