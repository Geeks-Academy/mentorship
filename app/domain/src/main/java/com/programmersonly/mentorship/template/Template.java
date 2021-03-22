package com.programmersonly.mentorship.template;

import lombok.*;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Builder
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PRIVATE)
public class Template {

    private UUID id;
    private UUID userId;
    private String email;
    private String fullName;
    private TemplateStatus status;


}
