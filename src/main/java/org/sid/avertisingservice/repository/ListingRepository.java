package org.sid.avertisingservice.repository;

import org.sid.avertisingservice.entities.Dealer;
import org.sid.avertisingservice.entities.Listing;
import org.sid.avertisingservice.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface ListingRepository extends JpaRepository<Listing, String> {

    List<Listing> findByDealerIdAndState(Dealer dealerId, State state);

    @Modifying
    @Transactional
    @Query("update Listing l set l.state = ?1 where l.id = ?2")
    void publishListingById(State state, UUID listingId);

    @Modifying
    @Transactional
    @Query("update Listing l set l.state = ?1 where l.id = ?2")
    void unpublishListingById(State state, UUID listingId);

//    @Query("update Listing l set l.state = " + State.PUBLISHED + " where l.id = ?1")
}
