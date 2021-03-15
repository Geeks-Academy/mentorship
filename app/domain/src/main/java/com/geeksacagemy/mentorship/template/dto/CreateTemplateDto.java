package com.geeksacagemy.mentorship.template.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateTemplateDto {

    private UUID userId;
    private String email;
    private String fullName;
}
