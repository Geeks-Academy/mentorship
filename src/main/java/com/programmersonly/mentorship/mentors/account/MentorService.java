package com.programmersonly.mentorship.mentors.account;

import com.programmersonly.mentorship.mentors.account.shared.CreateMentorEvent;

public interface MentorService {

    void createMentor(CreateMentorEvent createEvent);

}
