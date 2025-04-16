package io.toy.roomy.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Room {

    @Id @GeneratedValue
    private Long id;

    private String name;        // 공간명
    private String location;    // 위치
    private int capacity;       // 인원 or 좌석 수

    @Enumerated(EnumType.STRING)
    private RoomType roomType;  // CLASSROOM / STUDY_ROOM

    @OneToMany(mappedBy = "room")
    private List<Reservation> reservations = new ArrayList<>();
}