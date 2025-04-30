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

    @Id @GeneratedValue
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "member_id")
//    private Member member;
//
//    @ManyToOne
//    @JoinColumn(name = "room_id")
//    private Room room;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;
}
