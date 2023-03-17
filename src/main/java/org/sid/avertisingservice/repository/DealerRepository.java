package org.sid.avertisingservice.repository;

import org.sid.avertisingservice.entities.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealerRepository extends JpaRepository<Dealer, String> {

}
