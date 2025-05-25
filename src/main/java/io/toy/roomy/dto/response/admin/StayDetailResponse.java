package io.toy.roomy.dto.response.admin;

import io.toy.roomy.domain.Room;
import io.toy.roomy.domain.Stay;
import io.toy.roomy.domain.type.StayType;
import lombok.Getter;

@Getter
public class StayDetailResponse {
    private Long id;
    private String location;
    private String name;
    private StayType stayType;
    private String filePath;
    private String orgFileName;

    public static StayDetailResponse from(Stay stay) {
        StayDetailResponse dto = new StayDetailResponse();
        dto.id = stay.getId();
        dto.location = stay.getLocation();
        dto.name = stay.getName();
        dto.stayType = stay.getType();
        dto.filePath = stay.getFilePath();
        dto.orgFileName = stay.getOrgFileName();
        return dto;
    }
}
