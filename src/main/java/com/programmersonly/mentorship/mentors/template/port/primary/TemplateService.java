package com.programmersonly.mentorship.mentors.template.port.primary;

import com.programmersonly.mentorship.mentors.template.port.shared.ConfirmTemplateRequest;
import com.programmersonly.mentorship.mentors.template.port.shared.CreateTemplateRequest;
import com.programmersonly.mentorship.mentors.template.port.shared.RemoveTemplateRequest;

public interface TemplateService {
    void create(CreateTemplateRequest request);
    void confirm(ConfirmTemplateRequest request);
    void remove(RemoveTemplateRequest request);
}
