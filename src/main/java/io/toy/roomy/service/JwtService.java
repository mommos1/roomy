package io.toy.roomy.service;

import io.jsonwebtoken.Claims;

public interface JwtService {

    //토큰 발급
    String generateToken(String username, String role, String name);
    //토큰 검증
    Claims validateToken(String token);
}
