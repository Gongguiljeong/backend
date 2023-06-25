package com.gongguiljeong.domain.brand.service;


import com.gongguiljeong.domain.admin.domain.LoginRequest;
import com.gongguiljeong.domain.brand.domain.Brand;
import com.gongguiljeong.domain.brand.domain.BrandJoinRequest;
import com.gongguiljeong.domain.brand.repository.BrandCustomRepositoryImpl;
import com.gongguiljeong.domain.brand.repository.BrandRepository;
import com.gongguiljeong.domain.common.domain.exception.ExceptionCode;
import com.gongguiljeong.domain.common.domain.exception.GongguiljeongException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BrandService {
    private final BrandRepository brandRepository;
    private final BrandCustomRepositoryImpl brandCustomRepository;

    @Transactional
    public void join(BrandJoinRequest brandCreateRequest) {
        brandRepository.save(brandCreateRequest.toEntity());
    }


    public Brand readBrand(Long id) {
        return brandRepository.findById(id).orElseThrow(() -> new GongguiljeongException(ExceptionCode.BRAND_NOT_FOUND));

    }


    public Brand login(LoginRequest loginRequest) {
        Brand brand = brandRepository.findByUsername(loginRequest.getUsername()).orElseThrow(() -> new GongguiljeongException(ExceptionCode.BRAND_NOT_FOUND));
        if (!loginRequest.getPassword().equals(brand.getPassword())) {
            throw new GongguiljeongException(ExceptionCode.BRAND_NOT_FOUND);
        }
        return brand;
    }
}
