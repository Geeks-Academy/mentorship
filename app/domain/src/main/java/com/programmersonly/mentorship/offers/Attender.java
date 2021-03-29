package com.programmersonly.mentorship.offers;

import lombok.*;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Attender {

    private UUID id;

    private UUID attenderId;

    private Status status;

    public enum Status {
        REQUESTED,
        ACCEPTED;
    }
}
