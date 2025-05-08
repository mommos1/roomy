package io.toy.roomy.common.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private SecretKey secretKey;

    @PostConstruct //빈 초기화 시점에서 추가 작업이 필요할 때 사용하는 어노테이션
    public void init() {
        //secret 을 base64 인코딩
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    /**
     * jwt 발급
     * @param username 사용자 id
     * @param role 사용자 권한
     * @param name 사용자 이름
     * @return 발급된 token
     */
    public String generateToken(String username, String role, String name) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .subject(username)
                .claim("role", role)
                .claim("name", name)
                .issuedAt(now)
                .expiration(expiry)
                .signWith(secretKey) // 버전 0.11 이상은 key만 넘기면 알고리즘 자동 설정
                .compact();
    }

    /**
     * jwt 검증
     * @param token 검증 대상 token
     * @return 검증된 토큰
     */
    public Claims validateToken(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
