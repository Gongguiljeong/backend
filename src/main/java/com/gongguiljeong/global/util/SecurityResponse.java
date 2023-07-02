package com.gongguiljeong.global.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gongguiljeong.domain.common.domain.exception.ExceptionCode;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

import java.io.IOException;

import static com.gongguiljeong.domain.common.domain.exception.ExceptionCode.*;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public class SecurityResponse {

    //로그인 안함
    public static void unAuthentication(HttpServletResponse response) throws IOException {
        String result = getResponse(UN_AUTHENTICATION);
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(UNAUTHORIZED.value());
        response.getWriter().println(result);
    }

    //권한없는 페이지에 들어갈때
    public static void forbidden(HttpServletResponse response) throws IOException {
        String result = getResponse(FORBIDDEN);
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.getWriter().println(result);
    }


    private static String getResponse(ExceptionCode exceptionCode) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(exceptionCode);
    }
}
