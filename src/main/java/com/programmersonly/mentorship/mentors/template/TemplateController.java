package com.programmersonly.mentorship.mentors.template;

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

    private final TemplateService service;

    @PostMapping("/create")
    public ResponseEntity<Void> createMentorTemplate(@RequestBody CreateTemplateRequest dto){
        service.create(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/confirm")
    public ResponseEntity<Void> confirmMentorTemplate(@RequestBody ConfirmTemplateRequest dto){
        service.confirm(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> removeMentorTemplate(@RequestBody RemoveTemplateRequest dto){
        service.remove(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
