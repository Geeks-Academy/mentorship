package com.programmersonly.mentorship.commons.shared;

import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
public class UserId {
    @NonNull UUID userId;
}
