package com.programmersonly.mentorship.mentors.template.domain;

import com.programmersonly.mentorship.commons.shared.UserId;
import io.vavr.control.Option;

public interface MentorTemplates {
    Option<MentorTemplate> findBy(MentorTemplateId id);
    Option<MentorTemplate> findBy(UserId userId);

    MentorTemplate publish(MentorTemplateEvent event);
}
