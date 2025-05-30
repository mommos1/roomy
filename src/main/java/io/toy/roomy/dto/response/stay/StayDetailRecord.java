package io.toy.roomy.dto.response.stay;

import io.toy.roomy.domain.Stay;
import io.toy.roomy.domain.type.StayType;

/**
 * 숙소 정보 조회 시 사용
 */
public record StayDetailRecord(
    Long id,
    String location,
    String name,
    StayType stayType,
    String filePath,
    String orgFileName
){
    public static StayDetailRecord from(Stay stay) {
        return new StayDetailRecord(
                stay.getId(),
                stay.getLocation(),
                stay.getName(),
                stay.getType(),
                stay.getFilePath(),
                stay.getOrgFileName()
        );
    }
}
