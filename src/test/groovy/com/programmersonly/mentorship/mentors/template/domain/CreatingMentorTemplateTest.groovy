package com.programmersonly.mentorship.mentors.template.domain

import spock.lang.Specification

import static com.programmersonly.mentorship.mentors.template.domain.MentorTemplateFixture.notExistingTemplate

class CreatingMentorTemplateTest extends Specification {

    def 'user should be able to create mentor template'() {
        given:
            MentorTemplate template = notExistingTemplate()
        when:
            MentorTemplateEvent.MentorTemplateCreated created =  template.create()
        then:
            created.status == MentorTemplateStatus.CREATED
    }

}
