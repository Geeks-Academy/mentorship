package com.programmersonly.mentorship.reservations.mentorsTemplate.domain;

import com.programmersonly.mentorship.commons.shared.UserId;

import java.util.UUID;

public class MentorTemplateFixture {

    public static MentorTemplate notExistingTemplate() {
        return new MentorTemplate(null, anyUserId(), "Description", null);
    }

    public static MentorTemplate createdMentorTemplate() {
        return new MentorTemplate(anyMentorTemplateId(), anyUserId(), "Description", MentorTemplateStatus.CREATED);
    }

    public static MentorTemplate confirmedMentorTemplate() {
        return new MentorTemplate(anyMentorTemplateId(), anyUserId(), "Description", MentorTemplateStatus.CONFIRMED);
    }


    public static MentorTemplateId anyMentorTemplateId() {
        return mentorTemplateId(UUID.randomUUID());
    }

    static MentorTemplateId mentorTemplateId(UUID id){
        return new MentorTemplateId(id);
    }

    public static UserId anyUserId() {
        return userId(UUID.randomUUID());
    }

    static UserId userId(UUID id){
        return new UserId(id);
    }
}
