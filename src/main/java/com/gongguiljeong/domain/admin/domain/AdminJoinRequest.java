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
    private final String loginId;

    @NotBlank
    private final String password;

    @NotBlank
    private final String name;

    @NotBlank
    private final String email;

    @Digits(integer = 5, fraction = 0)
    private final Long brandId;

    public Admin toEntity(Brand brand, BCryptPasswordEncoder passwordEncoder) {
        return new Admin(brand, name, loginId,  passwordEncoder.encode(password), email);
    }
}
