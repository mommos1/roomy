package io.toy.roomy.repository;

import io.toy.roomy.domain.Accommodation;
import io.toy.roomy.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Accommodation, Long> {
    Optional<Accommodation> findByName(String username);
}
