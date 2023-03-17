package org.sid.avertisingservice.dtos;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.sid.avertisingservice.entities.Listing;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DealerDto {

    private String id;

    private String name;

}
