package com.gongguiljeong.domain.brand.repository;

import com.gongguiljeong.domain.brand.dto.BrandResponse;
import com.gongguiljeong.domain.brand.dto.BrandSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BrandCustomRepository {
    Page<BrandResponse> getBrandList(BrandSearchCondition brandSearchCondition, Pageable pageable);
}
