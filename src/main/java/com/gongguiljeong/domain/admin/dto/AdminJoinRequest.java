package com.gongguiljeong.domain.admin.dto;


import com.gongguiljeong.domain.admin.model.Admin;
import com.gongguiljeong.domain.brand.model.Brand;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@RequiredArgsConstructor
public class AdminJoinRequest {
    private final String loginId;
    private final String password;
    private final String name;
    private final String email;
    private final String brandName;
    private final String brandLink;

    public Admin toEntity(Brand brand, BCryptPasswordEncoder passwordEncoder) {
        return new Admin(brand, loginId, passwordEncoder.encode(password), name, email);
    }
}
