package com.gongguiljeong.domain.common.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRequest {

    private String username;
    private String password;

    @Builder
    private LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
