package io.toy.roomy.service;

import io.jsonwebtoken.Claims;

public class JwtServiceImpl implements JwtService {
    @Override
    public String generateToken(String username, String role, String name) {
        return "";
    }

    @Override
    public Claims validateToken(String token) {
        return null;
    }
}
