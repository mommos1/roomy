package io.toy.roomy.dto.request;

import io.toy.roomy.domain.type.StayType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StayRequest {
    private String name;
    private String location;
    private StayType type;
}
