package com.gongguiljeong.global.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gongguiljeong.domain.admin.model.Admin;
import com.gongguiljeong.domain.admin.model.Role;
import com.gongguiljeong.global.base.UserAdmin;

import java.time.Duration;
import java.util.Date;

public class Jwt {
    public static final String ACCESS_TOKEN_SECRET_KEY = "시크릿키";
    public static final String REFRESH_TOKEN_SECRET_KEY = "시크릿키";
    public static final long ACCESS_TOKEN_EXPIRATION_TIME = Duration.ofMillis(1).toMillis();
    public static final long REFRESH_TOKEN_EXPIRATION_TIME = Duration.ofDays(14).toMillis();
    public static final String PREFIX = "Bearer ";

    public static String createAccessToken(UserAdmin useradmin) {
        String jwtToken = JWT.create()
                .withSubject("jwt")
                .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME))
                .withClaim("id", useradmin.getId())
                .withClaim("role", useradmin.getRole().name())
                .sign(Algorithm.HMAC512(ACCESS_TOKEN_SECRET_KEY));
        return PREFIX + jwtToken;
    }



    public static String createRefreshToken(UserAdmin userADmin) {
        return JWT.create()
                .withSubject("jwt")
                .withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME))
                .withClaim("id", userADmin.getId())
                .withClaim("role", userADmin.getRole().name())
                .sign(Algorithm.HMAC512(REFRESH_TOKEN_SECRET_KEY));
    }


    public static UserAdmin accessTokenVerify(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(ACCESS_TOKEN_SECRET_KEY)).build().verify(token);
        Long id = decodedJWT.getClaim("id").asLong();
        String role = decodedJWT.getClaim("role").asString();
        return new Admin(id, Role.valueOf(role));
    }

    public static UserAdmin refreshTokenVerify(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(REFRESH_TOKEN_SECRET_KEY)).build().verify(token);
        Long id = decodedJWT.getClaim("id").asLong();
        String role = decodedJWT.getClaim("role").asString();
        return new Admin(id, Role.valueOf(role));

    }



}
