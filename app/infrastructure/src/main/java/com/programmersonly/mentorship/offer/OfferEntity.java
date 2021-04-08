package com.programmersonly.mentorship.offer;

import com.programmersonly.mentorship.offers.OfferState;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "offer")
@Getter
@Builder
@Setter
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

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AttenderEntity> attenders;

    @Enumerated(EnumType.STRING)
    private OfferState state;

    @Override
    public String toString() {
        return "OfferEntity{" +
                "offerId=" + offerId +
                ", ownerId=" + ownerId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", requestSet=" + attenders +
                ", state=" + state +
                '}';
    }
}