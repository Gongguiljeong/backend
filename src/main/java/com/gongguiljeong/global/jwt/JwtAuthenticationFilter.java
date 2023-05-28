package com.gongguiljeong.global.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gongguiljeong.domain.admin.dto.AdminLoginRequest;
import com.gongguiljeong.domain.admin.model.Admin;
import com.gongguiljeong.global.util.CustomResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.time.Duration;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.authenticationManager = authenticationManager;
    }

    //    원래 /login 하면 동작함
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            AdminLoginRequest adminLoginRequest = objectMapper.readValue(request.getInputStream(), AdminLoginRequest.class);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(adminLoginRequest.getLoginId(), adminLoginRequest.getPassword());
            return authenticationManager.authenticate(token);
        } catch (IOException e) {
            throw new InternalAuthenticationServiceException(e.getMessage());
        }
    }

    //로그인 실패하면 반환한다.
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        CustomResponse.unAuthentication(response, "로그인 실패 ");
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        Admin admin = (Admin) authResult.getPrincipal();
        String jwtToken = Jwt.createAccessToken(admin);
        String refreshToken = Jwt.createRefreshToken(admin);
        response.addHeader(HttpHeaders.AUTHORIZATION, jwtToken);
        ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                .maxAge(Duration.ofDays(14))
                .path("/")
//                .secure(true) //https를 쓸때 사용
                .sameSite("None") //csrf 공격을 방지하기 위해 설정
//                .domain("localhost:3000") // 도메인이 다르면 쿠키를 못받는다.
                .httpOnly(true)
                .build();
        response.setHeader("Set-Cookie", cookie.toString());
        AdminLoginRequest adminLoginRequest = new AdminLoginRequest(admin);
        CustomResponse.success(response, adminLoginRequest);

    }
}
