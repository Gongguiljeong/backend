package com.gongguiljeong.domain.interest_gongguiljeong.repository;

import com.gongguiljeong.domain.interest_gongguiljeong.domain.InterestGongguiljeong;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InterestGongguiljeongQueryRepository {
    Page<InterestGongguiljeong> findByMainCategoryIdAndSubCategoryId(Pageable pageable, Long mainCategoryId, Long subCategoryId);
}
