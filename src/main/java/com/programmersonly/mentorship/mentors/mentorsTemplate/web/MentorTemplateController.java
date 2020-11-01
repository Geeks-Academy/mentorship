package com.programmersonly.mentorship.mentors.mentorsTemplate.web;

import com.programmersonly.mentorship.commons.commands.Result;
import com.programmersonly.mentorship.mentors.mentorsTemplate.application.CreateMentorCommand;
import com.programmersonly.mentorship.mentors.mentorsTemplate.application.CreatingMentorTemplate;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/v1/mentors")
@AllArgsConstructor
public class MentorTemplateController {

    private final CreatingMentorTemplate creatingMentorTemplate;

    @PostMapping
    ResponseEntity createTemplate(@RequestBody CreateMentorTemplateDto dto){
       Try<Result> result = creatingMentorTemplate.create(
               new CreateMentorCommand(dto.getDescription(), dto.getUserId())
       );

       return result
               .map(success -> ResponseEntity.status(201).build())
               .getOrElseGet(failed -> ResponseEntity.status(INTERNAL_SERVER_ERROR).build());
    }
}

@RequiredArgsConstructor
@Getter
class CreateMentorTemplateDto {

    private final UUID userId;
    private final String description;

}
