package org.sid.avertisingservice.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sid.avertisingservice.config.InfrastructureTestConfig;
import org.sid.avertisingservice.entities.Dealer;
import org.sid.avertisingservice.entities.Listing;
import org.sid.avertisingservice.enums.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest(classes = {InfrastructureTestConfig.class})
@DirtiesContext
@Disabled
public class ListingRepositoryIntegrationTest {

    @Autowired
    private ListingRepository listingRepository;

    @Autowired
    private DealerRepository dealerRepository;

    Dealer dealer;

    Dealer savedDealer;

    @BeforeEach
    void setUp() {
        dealer = Dealer.builder().name("Eric").build();
        savedDealer = dealerRepository.save(dealer);
    }

    @AfterEach
    void cleanUp() {
        listingRepository.deleteAll();
        dealerRepository.deleteAll();
    }

    @Test
    void given_listing_not_exist_when_save_then_listing_persisted() {
        //GIVEN
        Listing listing = buildListing(savedDealer, "vehicle", 23.5, new Date());

        // WHEN
        final Listing savedListing = listingRepository.save(listing);

        //THEN
        assertThat(Objects.nonNull(listing.getId())).isTrue();
        assertThat(listing.getDealerId()).isEqualTo(savedListing.getDealerId());
        assertThat(listing.getVehicle()).isEqualTo(savedListing.getVehicle());
        assertThat(listing.getPrice()).isEqualTo(savedListing.getPrice());
        assertThat(listing.getCreatedAt()).isEqualTo(savedListing.getCreatedAt());
        assertThat(savedListing.getState()).isEqualTo(State.DRAFT);
    }

    @Test
    void given_listing_persisted_when_findById_then_listing_retrieved() {
        //GIVEN
        Listing listing = buildListing(savedDealer, "vehicle", 23.5, new Date());
        final Listing savedListing = listingRepository.save(listing);

        // WHEN
        final Optional<Listing> retrievedListing = listingRepository.findById(savedListing.getId());

        //THEN
        assertThat(retrievedListing).isPresent();
        assertThat(savedListing.getDealerId().getId()).isEqualTo(retrievedListing.get().getDealerId().getId());
        assertThat(savedListing.getVehicle()).isEqualTo(retrievedListing.get().getVehicle());
        assertThat(savedListing.getPrice()).isEqualTo(retrievedListing.get().getPrice());
        assertThat(savedListing.getCreatedAt()).isEqualTo(retrievedListing.get().getCreatedAt());
        assertThat(savedListing.getState()).isEqualTo(retrievedListing.get().getState());
    }

    @Test
    void given_listing_persisted_when_deleteById_then_listing_deleted() {
        //GIVEN
        Listing listing = buildListing(savedDealer, "vehicle", 23.5, new Date());
        final Listing savedListing = listingRepository.save(listing);

        // WHEN
        listingRepository.deleteById(savedListing.getId());
        final Optional<Listing> retrievedListing = listingRepository.findById(savedListing.getId());

        //THEN
        assertThat(retrievedListing).isEmpty();
    }

    @Test
    void given_listing_persisted_when_updated_and_save_then_listing_updated() {
        //GIVEN
        Listing listing = buildListing(savedDealer, "vehicle", 23.5, new Date());
        Listing savedListing = listingRepository.save(listing);
        savedListing.setVehicle("Updatevehicle");
        savedListing.setPrice(45.5);

        // WHEN
        final Listing updatedListing = listingRepository.save(savedListing);

        //THEN
        assertThat(updatedListing.getDealerId().getId()).isEqualTo(savedListing.getDealerId().getId());
        assertThat(updatedListing.getVehicle()).isEqualTo("Updatevehicle");
        assertThat(updatedListing.getPrice()).isEqualTo(45.5);
        assertThat(updatedListing.getCreatedAt()).isEqualTo(savedListing.getCreatedAt());
        assertThat(updatedListing.getState()).isEqualTo(savedListing.getState());
    }

    @Test
    void given_2_listing_persisted_with_same_dealerId_and_state_draft_when_findByDealerIdAndState_then_return_2_listing() {
        //GIVEN
        Listing firstListing = buildListing(savedDealer, "vehicle", 23.5, new Date());
        listingRepository.save(firstListing);

        Listing secondListing = buildListing(savedDealer, "vehicle", 23.5, new Date());
        listingRepository.save(secondListing);

        // WHEN
        final List<Listing> listings = listingRepository.findByDealerIdAndState(savedDealer,State.DRAFT);

        //THEN
        assertThat(listings).hasSize(2);
    }

    private Listing buildListing(Dealer dealerId, String vehicle, Number price, Date createdAt) {
        Listing listing = new Listing();
        listing.setDealerId(dealerId);
        listing.setVehicle(vehicle);
        listing.setCreatedAt(createdAt);
        listing.setPrice(price);
        return listing;
    }


}
