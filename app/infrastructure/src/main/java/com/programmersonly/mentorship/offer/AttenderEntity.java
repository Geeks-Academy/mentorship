package com.programmersonly.mentorship.offer;

import com.programmersonly.mentorship.offers.Attender;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Attender")
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class AttenderEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type = "uuid-char")
    private UUID id;

    private UUID attenderId;

    @Enumerated(EnumType.STRING)
    private Attender.Status status;

    @ManyToOne(optional = false)
    @JoinColumn(name="offer_id")
    private OfferEntity offer;
}
