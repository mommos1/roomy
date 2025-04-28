package io.toy.roomy.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String result;
    private String error;
    private String errorCode;

    public LoginResponse(String result, String error, String errorCode) {
        this.result = result;
        this.error = error;
        this.errorCode = errorCode;
    }
}
