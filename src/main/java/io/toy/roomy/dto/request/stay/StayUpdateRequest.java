package io.toy.roomy.dto.request.stay;

import io.toy.roomy.domain.type.StayType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StayUpdateRequest {
    private Long id;
    private String location;
    private String name;
    private StayType type;
    private String filePath;
    private String orgFilePath;
    private String orgFileName;
}
