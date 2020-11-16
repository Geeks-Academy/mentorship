package com.programmersonly.mentorship.mentors.template;

import java.util.UUID;

interface TemplateCommand {
    void save(TemplateEntity templateEntity);
    void deleteById(UUID templateId);
}
