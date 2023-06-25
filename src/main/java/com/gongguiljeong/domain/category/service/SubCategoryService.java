package com.gongguiljeong.domain.category.service;


import com.gongguiljeong.domain.category.domain.MainCategory;
import com.gongguiljeong.domain.category.domain.SubCategoryCreateRequest;
import com.gongguiljeong.domain.category.repository.MainCategoryRepository;
import com.gongguiljeong.domain.category.repository.SubCategoryRepository;
import com.gongguiljeong.domain.common.domain.exception.ExceptionCode;
import com.gongguiljeong.domain.common.domain.exception.GongguiljeongException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubCategoryService {
    private final SubCategoryRepository subCategoryRepository;
    private final MainCategoryRepository mainCategoryRepository;


    @Transactional
    public void createSubCategory(SubCategoryCreateRequest subcategoryService) {
        MainCategory mainCategory = mainCategoryRepository.findById(subcategoryService.getMainCategoryId()).orElseThrow(()-> new GongguiljeongException(ExceptionCode.MAIN_CATEGORY_NOT_FOUND));
        subCategoryRepository.save(subcategoryService.toEntity(mainCategory));
    }
}
