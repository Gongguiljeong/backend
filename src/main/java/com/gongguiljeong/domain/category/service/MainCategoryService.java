package com.gongguiljeong.domain.category.service;


import com.gongguiljeong.domain.category.repository.MainCategoryRepository;
import com.gongguiljeong.domain.category.domain.MainCategoryCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MainCategoryService {
    private final MainCategoryRepository mainCategoryRepository;

    @Transactional
    public void createMainCategory(MainCategoryCreateRequest mainCategoryCreateRequest) {
        mainCategoryRepository.save(mainCategoryCreateRequest.toEntity());
    }
}
