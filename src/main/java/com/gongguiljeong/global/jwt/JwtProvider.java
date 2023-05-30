package com.gongguiljeong.global.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gongguiljeong.domain.admin.model.Admin;
import com.gongguiljeong.domain.admin.model.Role;
import com.gongguiljeong.global.base_model.UserAdmin;

import java.time.Duration;
import java.util.Date;


public class JwtProvider {


    private final String ACCESS_TOKEN_SECRET_KEY = "7b190110066d09a1f88c5970fa55d5c2a61e52a2e4b984c7704966ab6bebad9791b5984136695239a67d98027ebcea2f36ba00ce628c662d7e0d43b5066d6567";
    private final String REFRESH_TOKEN_SECRET_KEY = "8937d7116cd6b9f184586f367e596d44661d838421db2fc23f7be1355df04d50838c9fbec843ef67cfe5856da75559ff69e48c3e6e00652f448fb975cc1c46d4";
    private static final long ACCESS_TOKEN_EXPIRATION_TIME = Duration.ofDays(1).toMillis();
    private static final long REFRESH_TOKEN_EXPIRATION_TIME = Duration.ofDays(14).toMillis();
    public static final String PREFIX = "Bearer ";

    public String createAccessToken(UserAdmin useradmin) {
        String jwtToken = JWT.create().withSubject("jwt").withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME)).withClaim("id", useradmin.getId()).withClaim("role", useradmin.getRole().name()).sign(Algorithm.HMAC512(ACCESS_TOKEN_SECRET_KEY));
        return PREFIX + jwtToken;
    }

    public String createRefreshToken(UserAdmin userADmin) {
        return JWT.create().withSubject("jwt").withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME)).withClaim("id", userADmin.getId()).withClaim("role", userADmin.getRole().name()).sign(Algorithm.HMAC512(REFRESH_TOKEN_SECRET_KEY));
    }

    public UserAdmin accessTokenVerify(String accessToken) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(ACCESS_TOKEN_SECRET_KEY)).build().verify(accessToken);
        Long id = decodedJWT.getClaim("id").asLong();
        String role = decodedJWT.getClaim("role").asString();
        return new Admin(id, Role.valueOf(role));
    }

    public UserAdmin refreshTokenVerify(String refreshToken) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(REFRESH_TOKEN_SECRET_KEY)).build().verify(refreshToken);
        Long id = decodedJWT.getClaim("id").asLong();
        String role = decodedJWT.getClaim("role").asString();
        return new Admin(id, Role.valueOf(role));
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
