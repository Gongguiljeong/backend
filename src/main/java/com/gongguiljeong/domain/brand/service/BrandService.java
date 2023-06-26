package com.gongguiljeong.domain.brand.service;


import com.gongguiljeong.domain.common.domain.LoginRequest;
import com.gongguiljeong.domain.brand.domain.Brand;
import com.gongguiljeong.domain.brand.domain.BrandJoinRequest;
import com.gongguiljeong.domain.brand.repository.BrandRepository;
import com.gongguiljeong.domain.common.domain.exception.ExceptionCode;
import com.gongguiljeong.domain.common.domain.exception.GongguiljeongException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.gongguiljeong.domain.common.domain.exception.ExceptionCode.BRAND_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BrandService {
    private final BrandRepository brandRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Brand join(BrandJoinRequest brandJoinRequest) {
        return brandRepository.save(brandJoinRequest.toEntity(passwordEncoder));
    }

    public Brand read(Long id) {
        return brandRepository.findById(id).orElseThrow(() -> new GongguiljeongException(BRAND_NOT_FOUND));

    }

    public Brand login(LoginRequest loginRequest) {
        Brand brand = brandRepository.findByUsername(loginRequest.getUsername()).orElseThrow(() -> new GongguiljeongException(BRAND_NOT_FOUND));
        brand.passwordValid(loginRequest.getPassword(), passwordEncoder);
        return brand;
    }
}
