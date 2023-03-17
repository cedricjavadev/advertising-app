package org.sid.avertisingservice.mapper;

import org.junit.jupiter.api.Test;
import org.sid.avertisingservice.dtos.DealerDto;
import org.sid.avertisingservice.entities.Dealer;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerMapperTest {

    @Test
    void given_dealer_id_map_dealerDto_id(){
        //GIVEN
        Dealer dealer = Dealer.builder().id(UUID.randomUUID().toString()).build();

        //WHEN
        final DealerDto dealerDto = DealerMapper.INSTANCE.dealerToDealerDto(dealer);

        //THEN
        assertThat(dealer.getId()).isEqualTo(dealerDto.getId());
    }

    @Test
    void given_dealer_name_map_dealerDto_name(){
        //GIVEN
        Dealer dealer = Dealer.builder().name("name").build();

        //WHEN
        final DealerDto dealerDto = DealerMapper.INSTANCE.dealerToDealerDto(dealer);

        //THEN
        assertThat(dealer.getName()).isEqualTo(dealerDto.getName());
    }

    @Test
    void given_dealerDto_id_map_dealer_id(){
        //GIVEN
        DealerDto dealerDto = DealerDto.builder().id(UUID.randomUUID().toString()).build();

        //WHEN
        final Dealer dealer = DealerMapper.INSTANCE.dealerDtoToDealer(dealerDto);

        //THEN
        assertThat(dealerDto.getName()).isEqualTo(dealer.getName());
    }


    @Test
    void given_dealerDto_name_map_dealer_name(){
        //GIVEN
        DealerDto dealerDto = DealerDto.builder().name("name").build();

        //WHEN
        final Dealer dealer = DealerMapper.INSTANCE.dealerDtoToDealer(dealerDto);

        //THEN
        assertThat(dealerDto.getId()).isEqualTo(dealer.getId());
    }
}
