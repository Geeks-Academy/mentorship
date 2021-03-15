package com.geeksacagemy.mentorship.direct.template;

import com.geeksacagemy.mentorship.template.TemplateService;
import com.geeksacagemy.mentorship.template.dto.CreateTemplateDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/template")
public class TemplateController {

    private static final Logger logger = LoggerFactory.getLogger(TemplateController.class);

    private TemplateService templateService;

    @PostMapping("/create")
    public ResponseEntity<Void> createMentorTemplate(@RequestBody CreateTemplateRequest request){
        CreateTemplateDto createTemplateDto = CreateTemplateDto.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .userId(request.getUserId())
                .build();
        templateService.create(createTemplateDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/confirm")
    public ResponseEntity<Void> confirmMentorTemplate(@RequestBody ConfirmTemplateRequest request){
        logger.info("confirmation method");
        templateService.confirm(request.getTemplateId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> removeMentorTemplate(@RequestBody RemoveTemplateRequest request){
        templateService.remove(request.getTemplateId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
