package com.programmersonly.mentorship.offers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Builder
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PRIVATE)
public class Offer {

    private UUID offerId;
    private UUID ownerId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Set<Attender> attenders;
    private OfferState state;

    @Override
    public String toString() {
        return "Offer{" +
                "offerId=" + offerId +
                ", ownerId=" + ownerId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", requestSet=" + attenders +
                ", state=" + state +
                '}';
    }
}
