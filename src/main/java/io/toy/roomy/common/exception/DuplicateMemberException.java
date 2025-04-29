package io.toy.roomy.common.exception;

/**
 * 중복 회원 예외
 */
public class DuplicateMemberException extends RuntimeException {
    public DuplicateMemberException(String message) {
        super(message);
    }
}
