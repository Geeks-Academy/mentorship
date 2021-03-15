package com.geeksacagemy.mentorship.direct.template;

import java.util.UUID;

public class TemplateRequestProvider {

    private static final UUID TEMPLATE_ID = UUID.fromString("893a767b-3d02-4a10-b8d5-e3628745eafa");
    private static final UUID USER_ID = UUID.fromString("893a767b-3d02-4a10-b8d5-e36287451111");
    private static final String EMAIL = "Phillip@test.pl";
    private static final String FULL_NAME = "Phillip";

    public static ConfirmTemplateRequest getConfirmTemplateRequest() {
        return ConfirmTemplateRequest.builder()
                .templateId(TEMPLATE_ID)
                .build();
    }

    public static CreateTemplateRequest getCreateTemplateRequest() {
        return CreateTemplateRequest.builder()
                .email(EMAIL)
                .fullName(FULL_NAME)
                .userId(USER_ID)
                .build();

    }

    public static RemoveTemplateRequest getRemoveTemplateRequest() {
        return RemoveTemplateRequest.builder()
                .templateId(TEMPLATE_ID)
                .build();

    }

}
