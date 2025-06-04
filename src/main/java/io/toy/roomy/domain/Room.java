package io.toy.roomy.domain;

import io.toy.roomy.dto.request.room.RoomRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Room {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,       // 시퀀스를 사용하겠다
            generator = "room_seq_generator"          // 어떤 시퀀스를? → 아래 정의된 이름
    )
    @SequenceGenerator(
            name = "room_seq_generator",              // JPA 내부에서 사용할 이름
            sequenceName = "room_seq",                // 실제 DB의 시퀀스 이름
            allocationSize = 1
    )
    private Long id;

    private String name;
    private int numberOfRooms;
    private int pricePerNight;
    private int capacity;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stay_id")
    private Stay stay;

    @OneToMany(mappedBy = "room")
    private List<Reservation> reservations = new ArrayList<>();

    public void update(RoomRequest dto) {
        this.name = dto.getName();
        this.numberOfRooms = dto.getNumberOfRooms();
        this.capacity = dto.getCapacity();
        this.pricePerNight = dto.getPricePerNight();
        this.description = dto.getDescription();
    }
}
