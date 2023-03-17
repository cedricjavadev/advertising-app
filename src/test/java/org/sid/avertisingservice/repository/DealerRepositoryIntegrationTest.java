package org.sid.avertisingservice.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sid.avertisingservice.config.InfrastructureTestConfig;
import org.sid.avertisingservice.entities.Dealer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest(classes = {InfrastructureTestConfig.class})
@DirtiesContext
@Disabled
public class DealerRepositoryIntegrationTest {

    @Autowired
    private DealerRepository dealerRepository;

    @AfterEach
    void cleanUp(){
        dealerRepository.deleteAll();
    }

    @Test
    void given_dealer_not_exist_when_save_then_dealer_persisted(){
        //GIVEN
        Dealer dealer = Dealer.builder().name("Eric").build();

        // WHEN
        final Dealer savedDealer = dealerRepository.save(dealer);

        //THEN
        assertThat(Objects.nonNull(savedDealer.getId())).isTrue();
        assertThat(dealer.getName()).isEqualTo(savedDealer.getName());
    }

    @Test
    void given_dealer_exist_when_findById_then_return_dealer_persisted(){
        //GIVEN
        Dealer dealer = Dealer.builder().name("Eric").build();
        final Dealer savedDealer = dealerRepository.save(dealer);

        // WHEN
        final Optional<Dealer> retrievedDealer = dealerRepository.findById(savedDealer.getId());

        //THEN
        assertThat(retrievedDealer).isPresent();
        assertThat(retrievedDealer.get().getId()).isEqualTo(savedDealer.getId());
        assertThat(retrievedDealer.get().getName()).isEqualTo(savedDealer.getName());
    }

    @Test
    void given_dealer_exist_when_deleteById_then_dealer_is_deleted(){
        //GIVEN
        Dealer dealer = Dealer.builder().name("Eric").build();
        final Dealer savedDealer = dealerRepository.save(dealer);

        // WHEN
        dealerRepository.deleteById(savedDealer.getId());
        final Optional<Dealer> retrievedDealer = dealerRepository.findById(savedDealer.getId());

        //THEN
        assertThat(retrievedDealer).isEmpty();
    }

    @Test
    void given_dealer_exist_when_updated_and_save_then_dealer_is_updated(){
        //GIVEN
        Dealer dealer = Dealer.builder().name("Eric").build();
        Dealer savedDealer = dealerRepository.save(dealer);
        savedDealer.setName("new name");

        // WHEN
        final Dealer updatedDealer = dealerRepository.save(savedDealer);

        //THEN
        assertThat(updatedDealer.getId()).isEqualTo(savedDealer.getId());
        assertThat(updatedDealer.getName()).isEqualTo(savedDealer.getName());
    }
}
