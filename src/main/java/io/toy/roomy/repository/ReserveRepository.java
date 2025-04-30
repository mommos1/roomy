package io.toy.roomy.repository;

import io.toy.roomy.domain.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReserveRepository extends JpaRepository<Accommodation, Long> {
}
