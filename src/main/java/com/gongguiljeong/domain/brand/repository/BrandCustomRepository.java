package com.gongguiljeong.domain.brand.repository;

import com.gongguiljeong.domain.brand.domain.BrandResponse;
import com.gongguiljeong.domain.brand.domain.BrandSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BrandCustomRepository {
    Page<BrandResponse> getBrandList(BrandSearchCondition brandSearchCondition, Pageable pageable);
}
