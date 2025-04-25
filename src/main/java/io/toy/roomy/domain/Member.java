package io.toy.roomy.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Member {
    @Id @GeneratedValue
    private Long id;

    private String username;    // 로그인 ID
    private String password;
    private String name;

    @Enumerated(EnumType.STRING)
    private MemberType memberType;          // USER / ADMIN

    // 예약 내역 (1:N)
    @OneToMany(mappedBy = "member")
    private List<Reservation> reservations = new ArrayList<>();
}
