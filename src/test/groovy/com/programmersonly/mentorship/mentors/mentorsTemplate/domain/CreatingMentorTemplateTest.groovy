package com.programmersonly.mentorship.mentors.mentorsTemplate.domain

import spock.lang.Specification

import static com.programmersonly.mentorship.mentors.mentorsTemplate.domain.MentorTemplateFixture.notExistingTemplate

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
