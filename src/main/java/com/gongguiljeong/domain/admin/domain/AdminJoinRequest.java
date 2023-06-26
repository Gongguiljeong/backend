package com.gongguiljeong.domain.admin.domain;


import com.gongguiljeong.domain.brand.domain.Brand;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
public class AdminJoinRequest {

    @NotBlank
    private final String username;

    @NotBlank
    private final String password;

    @NotBlank
    private final String name;

    @NotBlank
    private final String email;

    @Builder
    private AdminJoinRequest(String username, String password, String name, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }
}
