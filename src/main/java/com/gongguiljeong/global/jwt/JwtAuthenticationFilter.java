//package com.gongguiljeong.global.jwt;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.gongguiljeong.domain.common.domain.LoginRequest;
//import com.gongguiljeong.domain.admin.domain.Admin;
//import com.gongguiljeong.domain.common.domain.AuthenticationEntity;
//import com.gongguiljeong.domain.common.domain.Member;
//import com.gongguiljeong.global.util.SecurityResponse;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseCookie;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.InternalAuthenticationServiceException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import java.io.IOException;
//import java.time.Duration;
//
//
//public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//    private final AuthenticationManager authenticationManager;
//    private final JwtProvider jwtProvider;
//
//    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
//        super(authenticationManager);
//        this.jwtProvider = jwtProvider;
//        this.authenticationManager = authenticationManager;
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            LoginRequest user = objectMapper.readValue(request.getInputStream(), LoginRequest.class);
//            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
//            return authenticationManager.authenticate(token);
//        } catch (IOException e) {
//            throw new InternalAuthenticationServiceException(e.getMessage());
//        }
//    }
//
//    @Override
//    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
//        SecurityResponse.unAuthentication(response);
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
//        Member member = (Member) authResult.getPrincipal();
//        AuthenticationEntity authenticationEntity = new AuthenticationEntity(member.getId(), member.getRole());
//        String jwtToken = jwtProvider.createAccessToken(authenticationEntity);
//        String refreshToken = jwtProvider.createRefreshToken(authenticationEntity);
//        response.addHeader(HttpHeaders.AUTHORIZATION, jwtToken);
//        ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
//                .maxAge(Duration.ofDays(14))
//                .path("/")
////                .secure(true) //https를 쓸때 사용
//                .sameSite("None") //csrf 공격을 방지하기 위해 설정
////                .domain("localhost:3000") // 도메인이 다르면 쿠키를 못받는다.
//                .httpOnly(true)
//                .build();
//        response.setHeader("Set-Cookie", cookie.toString());
//        LoginRequest adminLoginRequest = new LoginRequest(member);
//        SecurityResponse.success(response, adminLoginRequest);
//
//    }
//}
