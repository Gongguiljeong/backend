package com.gongguiljeong.global.jwt;

import com.gongguiljeong.global.base_model.UserAdmin;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {


    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("jwt 인가 필터");
        JwtProvider jwtProvider = new JwtProvider();
        String accessToken = resolveToken(request);
        log.info("accessToken : {}", accessToken);
        if (StringUtils.hasText(accessToken) && jwtProvider.validateAccessToken(accessToken)) {
            UserAdmin userAdmin = jwtProvider.accessTokenVerify(accessToken);
            Authentication authentication = new UsernamePasswordAuthenticationToken(userAdmin, null, userAdmin.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String accessToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(accessToken) && accessToken.startsWith(JwtProvider.PREFIX)) {
            return accessToken.replace(JwtProvider.PREFIX, "");
        }
        return null;
    }
}
