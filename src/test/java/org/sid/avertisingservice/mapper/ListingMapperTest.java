package org.sid.avertisingservice.mapper;

import org.junit.jupiter.api.Test;
import org.sid.avertisingservice.dtos.DealerDto;
import org.sid.avertisingservice.dtos.ListingDto;
import org.sid.avertisingservice.entities.Dealer;
import org.sid.avertisingservice.entities.Listing;
import org.sid.avertisingservice.enums.State;

import java.util.Date;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class ListingMapperTest {

    @Test
    void given_listing_id_map_listingDto_id(){
        //GIVEN
        Listing listing = Listing.builder().id(UUID.randomUUID().toString()).build();

        //WHEN
        final ListingDto listingDto = ListingMapper.INSTANCE.listingToListingDto(listing);

        //THEN
        assertThat(listing.getId()).isEqualTo(listingDto.getId());
    }

    @Test
    void given_listing_dealerId_map_listingDto_dealerId(){
        //GIVEN
        Listing listing = Listing.builder().dealerId(Dealer.builder().name("name").build()).build();

        //WHEN
        final ListingDto listingDto = ListingMapper.INSTANCE.listingToListingDto(listing);

        //THEN
        assertThat(listing.getDealerId().getName()).isEqualTo(listingDto.getDealerId().getName());
    }

    @Test
    void given_listing_vehicle_map_listingDto_vehicle(){
        //GIVEN
        Listing listing = Listing.builder().vehicle("vehicle").build();

        //WHEN
        final ListingDto listingDto = ListingMapper.INSTANCE.listingToListingDto(listing);

        //THEN
        assertThat(listing.getVehicle()).isEqualTo(listingDto.getVehicle());
    }

    @Test
    void given_listing_price_map_listingDto_price(){
        //GIVEN
        Listing listing = Listing.builder().price(34.90).build();

        //WHEN
        final ListingDto listingDto = ListingMapper.INSTANCE.listingToListingDto(listing);

        //THEN
        assertThat(listing.getPrice()).isEqualTo(listingDto.getPrice());
    }

    @Test
    void given_listing_createdAt_map_listingDto_createdAt(){
        //GIVEN
        Listing listing = Listing.builder().createdAt(new Date()).build();

        //WHEN
        final ListingDto listingDto = ListingMapper.INSTANCE.listingToListingDto(listing);

        //THEN
        assertThat(listing.getCreatedAt()).isEqualTo(listingDto.getCreatedAt());
    }

    @Test
    void given_listing_state_map_listingDto_state(){
        //GIVEN
        Listing listing = Listing.builder().state(State.DRAFT).build();

        //WHEN
        final ListingDto listingDto = ListingMapper.INSTANCE.listingToListingDto(listing);

        //THEN
        assertThat(listing.getState()).isEqualTo(listingDto.getState());
    }

    @Test
    void given_listingDto_id_map_listing_id(){
        //GIVEN
        ListingDto listingDto = ListingDto.builder().id(UUID.randomUUID().toString()).build();

        //WHEN
        final Listing listing = ListingMapper.INSTANCE.listingDtoToListing(listingDto);

        //THEN
        assertThat(listing.getId()).isEqualTo(listingDto.getId());
    }

    @Test
    void given_listingDto_dealerId_map_listing_dealerId(){
        //GIVEN
        ListingDto listingDto = ListingDto.builder().dealerId(DealerDto.builder().name("name").build()).build();

        //WHEN
        final Listing listing = ListingMapper.INSTANCE.listingDtoToListing(listingDto);

        //THEN
        assertThat(listing.getDealerId().getName()).isEqualTo(listingDto.getDealerId().getName());
    }

    @Test
    void given_listingDto_vehicle_map_listing_vehicle(){
        //GIVEN
        ListingDto listingDto = ListingDto.builder().vehicle("vehicle").build();

        //WHEN
        final Listing listing = ListingMapper.INSTANCE.listingDtoToListing(listingDto);

        //THEN
        assertThat(listing.getVehicle()).isEqualTo(listingDto.getVehicle());
    }

    @Test
    void given_listingDto_price_map_listing_price(){
        //GIVEN
        ListingDto listingDto = ListingDto.builder().price(34.7).build();

        //WHEN
        final Listing listing = ListingMapper.INSTANCE.listingDtoToListing(listingDto);

        //THEN
        assertThat(listing.getPrice()).isEqualTo(listingDto.getPrice());
    }

    @Test
    void given_listingDto_createdAt_map_listing_createdAt(){
        //GIVEN
        ListingDto listingDto = ListingDto.builder().createdAt(new Date()).build();

        //WHEN
        final Listing listing = ListingMapper.INSTANCE.listingDtoToListing(listingDto);

        //THEN
        assertThat(listing.getCreatedAt()).isEqualTo(listingDto.getCreatedAt());
    }

    @Test
    void given_listingDto_state_map_listing_state(){
        //GIVEN
        ListingDto listingDto = ListingDto.builder().state(State.DRAFT).build();

        //WHEN
        final Listing listing = ListingMapper.INSTANCE.listingDtoToListing(listingDto);

        //THEN
        assertThat(listing.getState()).isEqualTo(listingDto.getState());
    }
}
