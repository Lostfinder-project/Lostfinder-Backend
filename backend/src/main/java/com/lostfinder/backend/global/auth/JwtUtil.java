package com.lostfinder.backend.global.auth;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtUtil {

    private final SecretKey key;
    private final Duration accessExpiration;

    public JwtUtil(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.access-expire}") long accessExpireMillis
    ) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessExpiration = Duration.ofMillis(accessExpireMillis);
    }

    /**
     * Access Token 생성
     */
    public String generateToken(Long memberId) {
        Instant now = Instant.now();

        return Jwts.builder()
                .subject(String.valueOf(memberId))      // 토큰 주제 (멤버 ID)
                .issuedAt(Date.from(now))                // 발급 시간
                .expiration(Date.from(now.plus(accessExpiration))) // 만료 시간
                .signWith(key)                           // HS256 기본 적용
                .compact();
    }

    /**
     * 토큰에서 멤버 ID(subject) 추출
     */
    public Long getMemberId(String token) {
        try {
            return Long.parseLong(getClaims(token).getPayload().getSubject());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 토큰 유효성 검사
     */
    public boolean validate(String token) {
        try {
            getClaims(token); // parsing 제대로 되면 OK
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    /**
     * Claims 파싱
     * parser() + verifyWith() = JJWT 0.12.x 최신 문법
     */
    private Jws<Claims> getClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .clockSkewSeconds(60) // 60초 오차 허용
                .build()
                .parseSignedClaims(token);
    }
}
