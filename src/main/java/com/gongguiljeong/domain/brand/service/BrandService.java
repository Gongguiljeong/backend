package com.gongguiljeong.domain.brand.service;


import com.gongguiljeong.domain.brand.domain.*;
import com.gongguiljeong.domain.brand.repository.BrandCustomRepositoryImpl;
import com.gongguiljeong.domain.brand.repository.BrandRepository;
import com.gongguiljeong.domain.common.domain.exception.ExceptionCode;
import com.gongguiljeong.domain.common.domain.exception.GongguiljeongException;
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

    //    @Transactional
//    public void deleteBrand(Long id) {
//        Brand brand = brandRepository.findById(id).orElseThrow(BrandNotFoundException::new);
//        if (brand.isUsed()) {
//            brand.delete();
//        }
//    }
//
//    @Transactional
//    public void restoreBrand(Long id) {
//        Brand brand = brandRepository.findById(id).orElseThrow(BrandNotFoundException::new);
//        if (!brand.isUsed()) {
//            brand.restore();
//        }
//    }
    @Transactional
    public void updateBrand(Long id, BrandUpdateRequest brandUpdateRequest) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new GongguiljeongException(ExceptionCode.BRAND_NOT_FOUND));
        brand.update(brandUpdateRequest);
    }

    public BrandResponse readBrand(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new GongguiljeongException(ExceptionCode.BRAND_NOT_FOUND));
        return new BrandResponse(brand);
    }

    public Page<BrandResponse> readBrandList(BrandSearchCondition brandSearchCondition, Pageable pageable) {
        return brandCustomRepository.getBrandList(brandSearchCondition, pageable);
    }

}
