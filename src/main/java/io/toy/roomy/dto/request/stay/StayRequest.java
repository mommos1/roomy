package io.toy.roomy.dto.request.stay;

import io.toy.roomy.domain.type.StayType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StayRequest {
    private String name;
    private String location;
    private StayType type;
    private String filePath;
    private String OrgFileName;
}
