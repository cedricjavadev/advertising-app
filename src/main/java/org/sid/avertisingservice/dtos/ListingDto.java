package org.sid.avertisingservice.dtos;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.sid.avertisingservice.entities.Dealer;
import org.sid.avertisingservice.enums.State;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ListingDto {

    private String id;

    private DealerDto dealerId;

    private String vehicle;

    private Number price;

    private Date createdAt;

    private State state;
}
