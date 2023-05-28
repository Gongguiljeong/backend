package com.gongguiljeong.global.jwt;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.gongguiljeong.domain.admin.model.Admin;
import com.gongguiljeong.global.util.CustomResponse;
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

import java.io.IOException;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("인가필터");
        String requestURI = request.getRequestURI();
        if (!requestURI.equals("/refresh") &&isHeaderVerify(request, response)) {
            try {
                String token = request.getHeader(HttpHeaders.AUTHORIZATION).replace(Jwt.PREFIX, "");
                Admin admin = (Admin) Jwt.accessTokenVerify(token);
                Authentication authentication = new UsernamePasswordAuthenticationToken(admin, null, admin.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                chain.doFilter(request, response);
            } catch (TokenExpiredException e) {
                CustomResponse.unAuthentication(response, "토큰이 만료됨");
            } catch (Exception e) {
                CustomResponse.unAuthentication(response, "토큰이 없음");
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    private boolean isHeaderVerify(HttpServletRequest request, HttpServletResponse response) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        return !(header == null) && header.startsWith(Jwt.PREFIX);
    }


}
