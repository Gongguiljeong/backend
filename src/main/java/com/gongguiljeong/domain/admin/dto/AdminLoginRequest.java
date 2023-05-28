package com.gongguiljeong.domain.admin.dto;


import com.gongguiljeong.domain.admin.model.Admin;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;

@Getter
public class AdminLoginRequest {


    @NotEmpty
    private final String loginId;
    @NotEmpty
    private final String password;

    public AdminLoginRequest(Admin admin) {
        this.loginId = admin.getLoginId();
        this.password = admin.getPassword();
    }
}
