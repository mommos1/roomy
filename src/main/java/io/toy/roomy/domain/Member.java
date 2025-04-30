package io.toy.roomy.domain;

import io.toy.roomy.domain.type.MemberType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(force = true)
@AllArgsConstructor
@Getter
@Builder
@Entity
public class Member {
    @Id @GeneratedValue
    private final Long id;

    private final String username; // 로그인 ID
    private final String password;
    private final String name;

    @Enumerated(EnumType.STRING)
    private final MemberType memberType; // USER / ADMIN
}

// 예약 내역 (1:N)
//    @OneToMany(mappedBy = "member")
//    private List<Reservation> reservation = new ArrayList<>();
