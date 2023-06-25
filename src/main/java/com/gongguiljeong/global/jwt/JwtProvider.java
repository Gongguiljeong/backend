package com.gongguiljeong.global.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gongguiljeong.domain.admin.domain.Admin;
import com.gongguiljeong.domain.admin.domain.Role;
import com.gongguiljeong.domain.common.domain.AuthenticationEntity;
import com.gongguiljeong.domain.common.domain.Member;
import com.gongguiljeong.domain.common.domain.UserAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;


@Component
public class JwtProvider {


    @Value("${jwt.access.secret}")
    private String ACCESS_TOKEN_SECRET_KEY;

    @Value("${jwt.refresh.secret}")
    private String REFRESH_TOKEN_SECRET_KEY;
    private static final long ACCESS_TOKEN_EXPIRATION_TIME = Duration.ofDays(1).toMillis();
    private static final long REFRESH_TOKEN_EXPIRATION_TIME = Duration.ofDays(14).toMillis();
    public static final String PREFIX = "Bearer ";

    public String createAccessToken(AuthenticationEntity authenticationEntity) {
        String jwtToken = JWT.create().withSubject("jwt").withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME)).withClaim("id", authenticationEntity.getId()).withClaim("role", authenticationEntity.getRole().name()).sign(Algorithm.HMAC512(ACCESS_TOKEN_SECRET_KEY));
        return PREFIX + jwtToken;
    }

    public String createRefreshToken(AuthenticationEntity authenticationEntity) {
        return JWT.create().withSubject("jwt").withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME)).withClaim("id", authenticationEntity.getId()).withClaim("role", authenticationEntity.getRole().name()).sign(Algorithm.HMAC512(REFRESH_TOKEN_SECRET_KEY));
    }

    public AuthenticationEntity accessTokenVerify(String accessToken) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(ACCESS_TOKEN_SECRET_KEY)).build().verify(accessToken);
        Long id = decodedJWT.getClaim("id").asLong();
        String role = decodedJWT.getClaim("role").asString();
        return new AuthenticationEntity(id, Role.valueOf(role));
    }

    public AuthenticationEntity refreshTokenVerify(String refreshToken) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(REFRESH_TOKEN_SECRET_KEY)).build().verify(refreshToken);
        Long id = decodedJWT.getClaim("id").asLong();
        String role = decodedJWT.getClaim("role").asString();
        return new AuthenticationEntity(id, Role.valueOf(role));
    }

    public boolean validateAccessToken(String accessToken) {
        try {
            JWT.require(Algorithm.HMAC512(ACCESS_TOKEN_SECRET_KEY)).build().verify(accessToken);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public boolean validateRefreshToken(String refreshToken) {
        try {
            JWT.require(Algorithm.HMAC512(REFRESH_TOKEN_SECRET_KEY)).build().verify(refreshToken);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

//    public void refreshTokenExpirationCheck(String token) {
//        DecodedJWT verify = JWT.require(Algorithm.HMAC512(ACCESS_TOKEN_SECRET_KEY)).build().verify(token);
//        if (verify.getExpiresAt().getTime() - System.currentTimeMillis() < 1000 * 60 * 60 * 24) {
//            throw new RuntimeException("토큰 만료");
//        }
//    }


}
