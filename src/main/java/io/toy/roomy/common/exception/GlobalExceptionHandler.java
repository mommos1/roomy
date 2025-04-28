package io.toy.roomy.common.exception;

import io.toy.roomy.dto.ErrorResponse;
import io.toy.roomy.dto.LoginResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 전역 예외처리
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateMemberException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateMember(DuplicateMemberException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT) // 409 Conflict
                .body(ErrorResponse.builder()
                        .type("localhost:8080/member/signup")
                        .title("DUPLICATE_MEMBER")
                        .status(409)
                        .detail(e.getMessage())
                        .instance("/signup")
                        .build());
    }
}
