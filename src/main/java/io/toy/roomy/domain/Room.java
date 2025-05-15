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
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stay_id")
    private Stay stay;
}
