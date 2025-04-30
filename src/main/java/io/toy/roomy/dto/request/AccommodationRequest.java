package io.toy.roomy.dto.request;

import io.toy.roomy.domain.type.AccommodationType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccommodationRequest {
    private String name;
    private String location;
    private AccommodationType type;
}
