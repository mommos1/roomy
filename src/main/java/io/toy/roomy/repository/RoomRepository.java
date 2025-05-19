package io.toy.roomy.repository;

import io.toy.roomy.domain.Member;
import io.toy.roomy.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByStayId(Long stayId);
}
