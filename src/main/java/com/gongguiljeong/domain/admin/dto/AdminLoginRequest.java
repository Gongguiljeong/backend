package com.gongguiljeong.domain.admin.dto;


import com.gongguiljeong.domain.admin.model.Admin;
import lombok.Data;
import lombok.Getter;

@Getter
public class AdminLoginRequest {

    private final String loginId;
    private final String password;

    public AdminLoginRequest(Admin admin) {
        this.loginId = admin.getLoginId();
        this.password = admin.getPassword();
    }
}
