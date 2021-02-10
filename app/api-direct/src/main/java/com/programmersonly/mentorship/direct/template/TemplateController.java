package com.programmersonly.mentorship.direct.template;

import com.programmersonly.mentorship.template.TemplateService;
import com.programmersonly.mentorship.template.dto.CreateTemplateDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/template")
class TemplateController {

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
        templateService.confirm(request.getTemplateId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> removeMentorTemplate(@RequestBody RemoveTemplateRequest request){
        templateService.remove(request.getTemplateId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
