package io.toy.roomy.common.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwtProviderTest {

    @Autowired
    JwtProvider jwtProvider;

    @Test
    void generateAndValidateToken() {
        // Given
        String username = "testuser";
        String role = "USER";
        String name = "홍길동";

        // When
        String token = jwtProvider.generateToken(username, role, name);
        Claims claims = jwtProvider.validateToken(token);

        System.out.println("Claims==== " + claims);

        assertThat(claims.getSubject()).isEqualTo(username);
        assertThat(claims.get("role")).isEqualTo(role);
        assertThat(claims.get("name")).isEqualTo(name);
    }
}