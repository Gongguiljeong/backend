package com.gongguiljeong.domain.category.domain;


import com.gongguiljeong.domain.category.domain.MainCategory;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MainCategoryCreateRequest {

    @NotBlank
    private  String name;

    public MainCategory toEntity() {
        return new MainCategory(name);
    }
}
