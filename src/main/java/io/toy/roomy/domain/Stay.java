package io.toy.roomy.domain;

import io.toy.roomy.domain.type.StayType;
import io.toy.roomy.dto.request.stay.StayUpdateRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private String filePath;
    private String orgFileName;

    private LocalDateTime regDt;

    public void update(StayUpdateRequest dto) {
        this.name = dto.getName();
        this.location = dto.getLocation();
        this.type = dto.getType();
        this.filePath = dto.getFilePath();
        this.orgFileName = dto.getOrgFileName();
    }

    @PrePersist
    public void prePersist() {
        this.regDt = LocalDateTime.now().withNano(0);;
    }

    @OneToMany(mappedBy = "stay", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Room> rooms = new ArrayList<>();
}

