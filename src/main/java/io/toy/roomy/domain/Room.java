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

    @ManyToOne
    @JoinColumn(name = "accommodation_id")
    private Accommodation accommodation;

//    @OneToMany(mappedBy = "room")
//    private List<RoomImage> images = new ArrayList<>();
}
