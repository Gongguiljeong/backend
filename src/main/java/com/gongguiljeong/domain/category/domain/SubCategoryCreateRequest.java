package com.gongguiljeong.domain.category.domain;


import com.gongguiljeong.domain.category.domain.MainCategory;
import com.gongguiljeong.domain.category.domain.SubCategory;
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
