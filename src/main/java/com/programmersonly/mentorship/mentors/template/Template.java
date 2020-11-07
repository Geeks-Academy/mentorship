package com.programmersonly.mentorship.mentors.template;

import lombok.*;

import java.util.UUID;

@Getter
@EqualsAndHashCode(of = "templateId")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
class Template {

    private final TemplateId templateId;
    @NonNull private final String description;

    Template(String description) {
        this((TemplateId) null, description);
    }

    Template(UUID templateId, String description) {
        this(new TemplateId(templateId), description);
    }
}
