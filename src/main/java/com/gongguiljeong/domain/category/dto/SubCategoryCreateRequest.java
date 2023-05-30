package com.gongguiljeong.domain.category.dto;


import com.gongguiljeong.domain.category.model.MainCategory;
import com.gongguiljeong.domain.category.model.SubCategory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SubCategoryCreateRequest {
    private final Long mainCategoryId;
    private final String name;

    public SubCategory toEntity(MainCategory mainCategory) {
        return new SubCategory(mainCategory, name);
    }
}
