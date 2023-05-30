package com.gongguiljeong.domain.category.service;


import com.gongguiljeong.domain.category.dto.SubCategoryCreateRequest;
import com.gongguiljeong.domain.category.exception.MainCategoryNotFoundException;
import com.gongguiljeong.domain.category.model.MainCategory;
import com.gongguiljeong.domain.category.repository.MainCategoryRepository;
import com.gongguiljeong.domain.category.repository.SubCategoryRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.util.stereotypes.ThreadSafe;
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
        MainCategory mainCategory = mainCategoryRepository.findById(subcategoryService.getMainCategoryId()).orElseThrow(MainCategoryNotFoundException::new);
        subCategoryRepository.save(subcategoryService.toEntity(mainCategory));
    }
}
