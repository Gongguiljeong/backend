package com.gongguiljeong.domain.gongguiljeong.repository;

import com.gongguiljeong.domain.gongguiljeong.domain.Gongguiljeong;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GongguiljeongQueryRepository {


    Page<Gongguiljeong> findByMainCategoryIdAndSubCategoryId(Long mainCategoryId, Long subCategoryId, Pageable pageable);
}
