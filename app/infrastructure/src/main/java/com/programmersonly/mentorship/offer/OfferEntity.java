package com.programmersonly.mentorship.offer;

import com.programmersonly.mentorship.offers.CanceledBy;
import com.programmersonly.mentorship.offers.OfferState;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Offer")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
class OfferEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type = "uuid-char")
    private UUID offerId;

    @Type(type = "uuid-char")
    private UUID ownerId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @ElementCollection
    private Set<UUID> requestSet;

    @Type(type = "uuid-char")
    private UUID attenderId;


    @Enumerated(EnumType.STRING)
    private CanceledBy canceledBy;

    private LocalDateTime gradeDate;

    @Enumerated(EnumType.STRING)
    private OfferState state;

    private int gradeValue;


    @Override
    public String toString() {
        return "OfferEntity{" +
                "offerId=" + offerId +
                ", ownerId=" + ownerId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", requestSet=" + requestSet +
                ", attenderId=" + attenderId +
                ", canceledById=" + canceledBy +
                ", gradeDate=" + gradeDate +
                ", state=" + state +
                ", gradeValue=" + gradeValue +
                '}';
    }
}