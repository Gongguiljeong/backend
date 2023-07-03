package com.gongguiljeong.domain.common.controller;

import com.gongguiljeong.domain.admin.domain.Admin;
import com.gongguiljeong.domain.admin.domain.Role;
import com.gongguiljeong.domain.admin.service.AdminService;
import com.gongguiljeong.domain.brand.domain.Brand;
import com.gongguiljeong.domain.brand.service.BrandService;
import com.gongguiljeong.domain.common.domain.AuthenticationEntity;
import com.gongguiljeong.domain.common.domain.LoginRequest;
import com.gongguiljeong.domain.user.domain.KakaoProfile;
import com.gongguiljeong.domain.user.domain.User;
import com.gongguiljeong.domain.user.service.KakaoService;
import com.gongguiljeong.domain.user.service.UserService;
import com.gongguiljeong.global.jwt.JwtProvider;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.Cookie;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.time.Duration;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final AdminService adminService;
    private final BrandService brandService;
    private final UserService userService;
    private final KakaoService kakaoService;
    private final JwtProvider jwtProvider;

    @PostMapping("/brands/login")
    public ResponseEntity<?> brandLogin(@Valid @RequestBody LoginRequest loginRequest) {
        Brand brand = brandService.login(loginRequest);
        return getResponseEntity(brand.getId(), brand.getRole());
    }

    @PostMapping("/admins/login")
    public ResponseEntity<?> adminLogin(@Valid @RequestBody LoginRequest loginRequest) {
        Admin admin = adminService.login(loginRequest);
        return getResponseEntity(admin.getId(), admin.getRole());
    }

/*    @GetMapping("/users/kakao")
    public void login(HttpServletResponse response) throws IOException {
        response.sendRedirect("https://kauth.kakao.com/oauth/authorize?client_id=c78d60a3b466b1b1c743a2bb745a1731&redirect_uri=http://localhost:8080/users/kakao/callback&response_type=code");
    }*/

    @GetMapping("/users/kakao")
    public ResponseEntity<?> kakaoLogin(@RequestParam(value = "code") String code) throws URISyntaxException {
        KakaoProfile kakaoProfile = kakaoService.getInfo(code);
        User user = userService.login(kakaoProfile);
        return getResponseEntity(user.getId(), user.getRole());
    }

    @Operation(summary = "리프레쉬 토큰 검증하고 엑세스토큰 반환")
    @PostMapping("/refresh")
    public ResponseEntity<?> getRefreshToken(@CookieValue(value = "refreshToken") Cookie cookie) {
        String refreshToken = cookie.getValue();
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            AuthenticationEntity authenticationEntity = jwtProvider.refreshTokenVerify(refreshToken);
            String accessToken = jwtProvider.createAccessToken(authenticationEntity);
            refreshToken = jwtProvider.createRefreshToken(authenticationEntity);
            ResponseCookie refreshTokenCookie = getRefreshTokenCookie(refreshToken);
            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString()).header(HttpHeaders.AUTHORIZATION, accessToken).build();
        }
        return ResponseEntity.badRequest().body("refreshtoken 없음");
    }


    private ResponseEntity<?> getResponseEntity(Long id, Role role) {
        AuthenticationEntity authenticationEntity = new AuthenticationEntity(id, role);
        String accessToken = jwtProvider.createAccessToken(authenticationEntity);
        String refreshToken = jwtProvider.createRefreshToken(authenticationEntity);
        ResponseCookie refreshTokenCookie = getRefreshTokenCookie(refreshToken);
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString()).header(HttpHeaders.AUTHORIZATION, accessToken).build();
    }

    private static ResponseCookie getRefreshTokenCookie(String refreshToken) {
        return ResponseCookie.from("refreshToken", refreshToken).maxAge(Duration.ofDays(14)).path("/")
//                .secure(true) //https를 쓸때 사용
//                .sameSite("None") //csrf 공격을 방지하기 위해 설정
//                .domain("localhost:3000") // 도메인이 다르면 쿠키를 못받는다.
                .httpOnly(true).build();
    }
}
