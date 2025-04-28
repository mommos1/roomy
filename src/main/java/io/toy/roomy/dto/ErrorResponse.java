package io.toy.roomy.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorResponse {
    private String type;
    private String title;
    private int status;
    private String detail;
    private String instance;
}

