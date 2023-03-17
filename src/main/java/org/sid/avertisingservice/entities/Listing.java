package org.sid.avertisingservice.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.sid.avertisingservice.enums.State;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class Listing {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @ManyToOne
    private Dealer dealerId;

    @Column(nullable = false)
    private String vehicle;

    @Column(nullable = false)
    private Number price;

    @Column(nullable = false)
    private Date createdAt;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private State state = State.DRAFT;
}
