package com.tang.wx.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    @Value("${custom.jwt.secrect}")
    private String secrect;

//    private long timeout;

    public String  createToken(int userId) {
//        long timeout = new Date().getTime() + this.timeout;
        JWTCreator.Builder builder = JWT.create();
        String token = builder
                .withClaim("userId", userId)
//                .withExpiresAt(new Date(timeout))
                .sign(this.getAlgorithm());
        return token;
    }

    public void verifierToken(String token) {
        JWTVerifier jwtVerifier = JWT.require(this.getAlgorithm()).build();
        jwtVerifier.verify(token);
    }

    public int getUserId(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            return decodedJWT.getClaim("userId").asInt();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public Algorithm getAlgorithm() {
        return Algorithm.HMAC256(this.secrect);
    }
}
