package io.toy.roomy.repository;

import io.toy.roomy.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReserveRepository extends JpaRepository<Reservation, Long> {
}
