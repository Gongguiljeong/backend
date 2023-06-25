package com.gongguiljeong.domain.admin.domain;


import com.gongguiljeong.domain.admin.domain.Admin;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdminLoginRequest {

    @NotBlank
    private String loginId;
    @NotBlank
    private String password;

    public AdminLoginRequest(Admin admin) {
        this.loginId = admin.getLoginId();
        this.password = admin.getPassword();
    }
}
