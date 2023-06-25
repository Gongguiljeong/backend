package com.gongguiljeong.domain.admin.domain;


import com.gongguiljeong.domain.brand.domain.Brand;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@RequiredArgsConstructor
public class AdminJoinRequest {

    @NotBlank
    private final String username;

    @NotBlank
    private final String password;

    @NotBlank
    private final String name;

    @NotBlank
    private final String email;

    private final Long brandId;

    public Admin toEntity(Brand brand, BCryptPasswordEncoder passwordEncoder) {
        return new Admin(brand, name, username,  passwordEncoder.encode(password), email);
    }
}
