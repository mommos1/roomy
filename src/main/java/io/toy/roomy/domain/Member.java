package io.toy.roomy.domain;

import io.toy.roomy.domain.type.MemberType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
public class Member {
    @Id @GeneratedValue
    private Long id;

    private String username; // 로그인 ID
    private String password;
    private String name;

    @Enumerated(EnumType.STRING)
    private MemberType memberType;          // USER / ADMIN

    // 예약 내역 (1:N)
    @OneToMany(mappedBy = "member")
    private List<Room> reservations = new ArrayList<>();
}
