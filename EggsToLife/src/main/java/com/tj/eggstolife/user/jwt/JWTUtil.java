package com.tj.eggstolife.user.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

// Spring 에서 Bean으로 관리되기 위함임
@Component
public class JWTUtil {
    // secretKey 라는 객체 변수를 만들어줌
    private SecretKey secretKey;

    // String type으로 받은 ScretKey 를 객체 변수로 암호화 하는 과정
    public JWTUtil(@Value("${spring.jwt.secret}") String secret){
        this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    // 아래 생성된 토큰을 검증하는 메서드들입니다.

    public String getUsername(String token){
        // 생성된 것이 우리가 발급한 키가 맞는지 검증하는 메서드 입니다.
        return Jwts.parser().verifyWith(secretKey).build().parseClaimsJws(token).getPayload().get("username", String.class);
    }

    public String getRole(String token){
        return Jwts.parser().verifyWith(secretKey).build().parseClaimsJws(token).getPayload().get("role", String.class);
    }

    public Boolean isExpired(String token){

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    // Token을 생성하는 메서드

    public String createdJwt(String username, String role, Long expiredMs){

        return Jwts.builder()
                .claim("username", username)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                // 암호화
                .signWith(secretKey)
                .compact();

    }

}
