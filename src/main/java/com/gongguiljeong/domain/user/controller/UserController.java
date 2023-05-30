package com.gongguiljeong.domain.user.controller;


import com.gongguiljeong.domain.user.dto.KakaoProfile;
import com.gongguiljeong.domain.user.model.User;
import com.gongguiljeong.domain.user.service.KakaoService;
import com.gongguiljeong.domain.user.service.UserService;
import com.gongguiljeong.global.base_model.UserAdmin;
import com.gongguiljeong.global.jwt.JwtProvider;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {


    private final UserService userService;
    private final KakaoService kakaoService;

    @GetMapping("/kakao")
    public void login(HttpServletResponse response) throws IOException {
        response.sendRedirect("https://kauth.kakao.com/oauth/authorize?client_id=c78d60a3b466b1b1c743a2bb745a1731&redirect_uri=http://localhost:8080/user/kakao/callback&response_type=code");
    }
    @GetMapping("/kakao/callback")
    public ResponseEntity<?> kakaoLogin(@RequestParam(value = "code") String code) throws URISyntaxException {
        JwtProvider jwt = new JwtProvider();
        KakaoProfile kakaoProfile = kakaoService.getInfo(code);
        User user = userService.login(kakaoProfile);
        String accessToken = jwt.createAccessToken(user);
        String refreshToken = jwt.createRefreshToken(user);
        ResponseCookie refreshTokenCookie = getRefreshTokenCookie(refreshToken);
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString()).header(HttpHeaders.AUTHORIZATION, accessToken).build();
    }


    @Operation(summary = "리프레쉬 토큰 검증하고 엑세스토큰 반환")
    @PostMapping("/refresh")
    public ResponseEntity<?> getInfo(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        JwtProvider jwtProvider = new JwtProvider();
        String refreshToken = getRefreshToken(cookies);
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            UserAdmin userAdmin = jwtProvider.refreshTokenVerify(refreshToken);
            String accessToken = jwtProvider.createAccessToken(userAdmin);
            refreshToken = jwtProvider.createRefreshToken(userAdmin);
            ResponseCookie refreshTokenCookie = getRefreshTokenCookie(refreshToken);
            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString()).header(HttpHeaders.AUTHORIZATION, accessToken).build();
        }
        return ResponseEntity.badRequest().body("refreshtoken이 없슴 로그인하셈");
    }

    private String getRefreshToken(Cookie[] cookies) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("refreshToken")) {
                return cookie.getValue();
            }
        }
        return null;
    }

    private static ResponseCookie getRefreshTokenCookie(String refreshToken) {
        return ResponseCookie.from("refreshToken", refreshToken).maxAge(Duration.ofDays(14)).path("/")
//                .secure(true) //https를 쓸때 사용
//                .sameSite("None") //csrf 공격을 방지하기 위해 설정
//                .domain("localhost:3000") // 도메인이 다르면 쿠키를 못받는다.
                .httpOnly(true).build();
    }

}
