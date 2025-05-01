package io.toy.roomy.domain;

import io.toy.roomy.domain.type.StayType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
public class Stay {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String location;
    private StayType type; // HOTEL, MOTEL, PENSION

//    @OneToMany(mappedBy = "room_id")
//    private List<Room> rooms = new ArrayList<>();
}

