package io.toy.roomy.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reservation {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,       // 시퀀스를 사용하겠다
            generator = "reservation_seq_generator"          // 어떤 시퀀스를? → 아래 정의된 이름
    )
    @SequenceGenerator(
            name = "reservation_seq_generator",              // JPA 내부에서 사용할 이름
            sequenceName = "reservation_seq",                // 실제 DB의 시퀀스 이름
            allocationSize = 1
    )
    private Long id;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int reservationCount;
    private String reserveName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

}
