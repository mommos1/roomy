package io.toy.roomy.domain;

import io.toy.roomy.domain.type.AccommodationType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
public class Accommodation {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String location;
    private AccommodationType type; // HOTEL, MOTEL, PENSION

//    @OneToMany(mappedBy = "room_id")
//    private List<Room> rooms = new ArrayList<>();
}

