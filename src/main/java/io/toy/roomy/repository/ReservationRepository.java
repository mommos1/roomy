package io.toy.roomy.repository;

import io.toy.roomy.domain.Stay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Stay, Long> {
    Optional<Stay> findByName(String name);
}
