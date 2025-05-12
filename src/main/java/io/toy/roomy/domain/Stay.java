package io.toy.roomy.domain;

import io.toy.roomy.domain.type.StayType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    @Enumerated(EnumType.STRING)
    private StayType type; // HOTEL, MOTEL, PENSION

    private LocalDateTime regDt;

    @PrePersist
    public void prePersist() {
        this.regDt = LocalDateTime.now().withNano(0);;
    }

//    @OneToMany(mappedBy = "room_id")
//    private List<Room> rooms = new ArrayList<>();
}

