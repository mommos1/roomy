package io.toy.roomy.dto.request;

import io.toy.roomy.domain.type.StayType;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
public class StayRequest {
    private String name;
    private String location;
    private StayType type;
}
