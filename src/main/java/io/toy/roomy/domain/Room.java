package io.toy.roomy.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Room {
    @Id @GeneratedValue
    private Long id;

    private String name;
    private int pricePerNight;
    private int capacity;

//    @OneToMany(mappedBy = "room")
//    private List<Reservation> reservations = new ArrayList<>();

//    @OneToMany(mappedBy = "room")
//    private List<RoomImage> images = new ArrayList<>();
}
