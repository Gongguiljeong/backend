package com.gongguiljeong.domain.admin.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRequest {

    private Long id;
    private String username;
    private String password;

    public LoginRequest(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
