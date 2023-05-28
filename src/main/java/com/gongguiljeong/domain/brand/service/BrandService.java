package com.gongguiljeong.domain.brand.service;


import com.gongguiljeong.domain.brand.dto.BrandCreateRequest;
import com.gongguiljeong.domain.brand.dto.BrandResponse;
import com.gongguiljeong.domain.brand.dto.BrandSearchCondition;
import com.gongguiljeong.domain.brand.dto.BrandUpdateRequest;
import com.gongguiljeong.domain.brand.exception.AlreadyDeletedBrandException;
import com.gongguiljeong.domain.brand.exception.BrandNotFoundException;
import com.gongguiljeong.domain.brand.model.Brand;
import com.gongguiljeong.domain.brand.repository.BrandCustomRepositoryImpl;
import com.gongguiljeong.domain.brand.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BrandService {
    private final BrandRepository brandRepository;
    private final BrandCustomRepositoryImpl brandCustomRepository;

    @Transactional
    public void createBrand(BrandCreateRequest brandCreateRequest) {
        brandRepository.save(brandCreateRequest.toEntity());
    }

    @Transactional
    public void deleteBrand(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(BrandNotFoundException::new);
        if (brand.isUsed()) {
            brand.delete();
            return;
        }
        throw new AlreadyDeletedBrandException();
    }

    @Transactional
    public void updateBrand(Long id, BrandUpdateRequest brandUpdateRequest) {
        Brand brand = brandRepository.findById(id).orElseThrow(BrandNotFoundException::new);
        brand.update(brandUpdateRequest);
    }

    public BrandResponse readBrand(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(BrandNotFoundException::new);
        return new BrandResponse(brand);
    }

    public Page<BrandResponse> getBrandList(BrandSearchCondition brandSearchCondition, Pageable pageable) {
        return brandCustomRepository.getBrandList(brandSearchCondition, pageable);
    }

}
