package com.programmersonly.mentorship.web;

import com.programmersonly.mentorship.mentors.template.TemplateFacade;
import com.programmersonly.mentorship.mentors.template.port.primary.TemplateService;
import com.programmersonly.mentorship.mentors.template.port.shared.ConfirmTemplateRequest;
import com.programmersonly.mentorship.mentors.template.port.shared.CreateTemplateRequest;
import com.programmersonly.mentorship.mentors.template.port.shared.RemoveTemplateRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/template")
class TemplateController {

    private final TemplateFacade facade;

    @PostMapping("/create")
    public ResponseEntity<Void> createMentorTemplate(@RequestBody CreateTemplateRequest dto){
        facade.getTemplateService().create(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/confirm")
    public ResponseEntity<Void> confirmMentorTemplate(@RequestBody ConfirmTemplateRequest dto){
        facade.getTemplateService().confirm(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> removeMentorTemplate(@RequestBody RemoveTemplateRequest dto){
        facade.getTemplateService().remove(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
