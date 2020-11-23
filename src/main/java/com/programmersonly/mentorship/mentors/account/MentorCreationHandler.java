package com.programmersonly.mentorship.mentors.account;

import com.programmersonly.mentorship.commons.exception.BasicErrorResponse;
import com.programmersonly.mentorship.commons.exception.BusinessException;
import com.programmersonly.mentorship.mentors.account.shared.CreateMentorEvent;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
class MentorCreationHandler {

    private final MentorEntityRepository repository;
    private final EntityModelMapper modelMapper;

    @EventListener
    public void handle(CreateMentorEvent createEvent) {
        repository.findById(createEvent.getUserId())
                .ifPresent(ignore -> {
                    throw BusinessException.ex409(new BasicErrorResponse("MS-03", "There is another mentor account associate with user id"));
                });

        saveMentor(modelMapper.map(createEvent));
    }

    private void saveMentor(MentorEntity entity){
        repository.save(entity);
    }

    public static class EntityModelMapper {

        public MentorEntity map(CreateMentorEvent event) {
            return new MentorEntity(event.getUserId());
        }

    }
}
