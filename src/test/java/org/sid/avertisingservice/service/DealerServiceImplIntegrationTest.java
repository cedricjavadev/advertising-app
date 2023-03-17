package org.sid.avertisingservice.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sid.avertisingservice.config.InfrastructureTestConfig;
import org.sid.avertisingservice.dtos.DealerDto;
import org.sid.avertisingservice.exceptions.DealerNotFoundException;
import org.sid.avertisingservice.repository.DealerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest(classes = {InfrastructureTestConfig.class})
@DirtiesContext
public class DealerServiceImplIntegrationTest {

    @Autowired
    private DealerRepository dealerRepository;

    @Autowired
    private DealerService dealerService;

    @AfterEach
    void cleanUp(){
        dealerRepository.deleteAll();
    }

    @Test
    void given_dealer_not_exist_when_createDealer_then_dealer_persisted(){
        //GIVEN
        DealerDto dealerDto = DealerDto.builder().name("Eric").build();

        // WHEN
        final DealerDto savedDealerDto = dealerService.createDealer(dealerDto);

        //THEN
        assertThat(Objects.nonNull(savedDealerDto.getId())).isTrue();
        assertThat(dealerDto.getName()).isEqualTo(savedDealerDto.getName());
    }

    @Test
    void given_dealer_persisted_when_getDealerById_then_return_dealerDto() throws DealerNotFoundException {
        //GIVEN
        DealerDto dealerDto = DealerDto.builder().name("Eric").build();
        final DealerDto savedDealerDto = dealerService.createDealer(dealerDto);

        //WHEN
        final DealerDto retrievedDealerDto = dealerService.getDealerById(savedDealerDto.getId());

        //THEN
        assertThat(savedDealerDto.getName()).isEqualTo(retrievedDealerDto.getName());
        assertThat(savedDealerDto.getId()).isEqualTo(retrievedDealerDto.getId());
    }

    @Test
    void given_dealer_not_exist_when_getDealerById_then_return_throw_DealerNotFoundException(){
        //GIVEN
        DealerDto dealerDto = DealerDto.builder().name("Eric").build();

        //WHEN
        Exception exception = Assertions.assertThrows(DealerNotFoundException.class,() -> {
            dealerService.getDealerById(UUID.randomUUID().toString());
        });

        //THEN
        Assertions.assertEquals("Dealer Not Found",exception.getMessage());
    }

    @Test
    void given_dealer_exist_when_deleteById_then_dealer_does_not_exists()  {
        //GIVEN
        DealerDto dealerDto = DealerDto.builder().name("Eric").build();
        final DealerDto savedDealerDto = dealerService.createDealer(dealerDto);

        // WHEN
        dealerService.deleteDealerById(savedDealerDto.getId());
        Exception exception = Assertions.assertThrows(DealerNotFoundException.class,() -> {
            dealerService.getDealerById(savedDealerDto.getId());
        });

        //THEN
        Assertions.assertEquals("Dealer Not Found",exception.getMessage());
    }

    @Test
    void given_dealer_persisted_updated_when_updateDealer_then_return_dealer_updated() {
        //GIVEN
        DealerDto dealerDto = DealerDto.builder().name("Eric").build();
        DealerDto savedDealerDto = dealerService.createDealer(dealerDto);
        savedDealerDto.setName("updateName");

        //WHEN
        final DealerDto updatedDealerDto = dealerService.updateDealer(savedDealerDto);

        //THEN
        assertThat("updateName").isEqualTo(updatedDealerDto.getName());
        assertThat(savedDealerDto.getId()).isEqualTo(updatedDealerDto.getId());
    }

    @Test
    void given_2_dealer_persisted_when_getAllDealer_then_return_list_of_2_dealers()  {
        //GIVEN
        DealerDto firstDealerDto = DealerDto.builder().name("Eric").build();
        dealerService.createDealer(firstDealerDto);

        DealerDto secondDealerDto = DealerDto.builder().name("Erica").build();
        dealerService.createDealer(secondDealerDto);

        //WHEN
        final List<DealerDto> dealerDtos = dealerService.getAllDealer();

        //THEN
        assertThat(dealerDtos).hasSize(2);

    }
}
