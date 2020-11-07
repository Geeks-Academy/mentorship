package com.programmersonly.mentorship.mentors.template;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/template")
class TemplateController {

    private final TemplateService templateService;

    @PostMapping
    ResponseEntity<CreatedTemplateDto> createTemplate(@RequestBody CreateTemplateDto dto){
        return ResponseEntity.ok(templateService.createTemplate(dto));
    }
}


@Getter
class CreateTemplateDto{

    private final String description;

    @JsonCreator
    public CreateTemplateDto(@JsonProperty("description") String description) {
        this.description = description;
    }
}

@AllArgsConstructor
@Getter
class CreatedTemplateDto {
    private final UUID templateId;
}
