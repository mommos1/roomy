package io.toy.roomy.domain;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
public class Member {
    @Id @GeneratedValue
    private Long id;

    private String username;    // 로그인 ID
    private String password;
    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;          // USER / ADMIN

    // 예약 내역 (1:N)
    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations = new ArrayList<>();
}
