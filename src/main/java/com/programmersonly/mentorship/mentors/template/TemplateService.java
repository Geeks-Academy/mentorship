package com.programmersonly.mentorship.mentors.template;

interface TemplateService {
    void create(CreateTemplateRequest request);
    void confirm(ConfirmTemplateRequest request);
    void remove(RemoveTemplateRequest request);
}
