package com.programmersonly.mentorship.mentors.account;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class MentorConfig {

    @Bean
    MentorCreationHandler.EntityModelMapper entityModelMapper(){
        return new MentorCreationHandler.EntityModelMapper();
    }

    @Bean
    MentorCreationHandler handler(MentorEntityRepository repository, MentorCreationHandler.EntityModelMapper modelMapper){
        return new MentorCreationHandler(repository, modelMapper);
    }
}
