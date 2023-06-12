package com.gongguiljeong.global.config;

import com.gongguiljeong.domain.admin.model.Role;
import com.gongguiljeong.global.jwt.JwtAuthenticationFilter;
import com.gongguiljeong.global.jwt.JwtAuthorizationFilter;
import com.gongguiljeong.global.util.CustomResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    public static class CustomSecurityFilterManager extends AbstractHttpConfigurer<CustomSecurityFilterManager, HttpSecurity> {
        @Override
        public void configure(HttpSecurity http) {
            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
            http.addFilter(new JwtAuthenticationFilter(authenticationManager));
            http.addFilter(new JwtAuthorizationFilter(authenticationManager));
        }
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //세션 관리를 안해줌
        http.sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.httpBasic(AbstractHttpConfigurer::disable);
        http.formLogin(AbstractHttpConfigurer::disable);
        http.csrf(AbstractHttpConfigurer::disable);
        http.headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

        //필터적용
        http.apply(new CustomSecurityFilterManager());


        //인증 실패  유요한 토큰을 가지고 있는지
        http.exceptionHandling(configurer -> configurer.authenticationEntryPoint((request, response, accessDeniedException) -> CustomResponse.unAuthentication(response, "로그인이 필요합니다.")));

        //권한 실패  hasRole
        http.exceptionHandling(configurer -> configurer.accessDeniedHandler((request, response, accessDeniedException) -> CustomResponse.forbidden(response)));
        http.authorizeHttpRequests(authorize -> authorize.requestMatchers(HttpMethod.GET, "/admins").authenticated().anyRequest().permitAll());
        return http.build();
    }


    @Bean
    public CorsConfigurationSource configurationSource() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOriginPattern("*");
        corsConfig.addAllowedHeader("*");
        corsConfig.addAllowedMethod("*");
//        corsConfig.setAllowedMethods(Arrays.asList("GET","POST"));
//        corsConfig.setAllowedOrigins(Arrays.asList("https://example.com"));
//        corsConfig.setAllowedHeaders(List.of("Authorization"));
        corsConfig.addExposedHeader(HttpHeaders.AUTHORIZATION);
        corsConfig.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);
        return source;
    }

}
