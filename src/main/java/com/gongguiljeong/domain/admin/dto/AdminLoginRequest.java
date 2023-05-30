package com.gongguiljeong.domain.admin.dto;


import com.gongguiljeong.domain.admin.model.Admin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
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
