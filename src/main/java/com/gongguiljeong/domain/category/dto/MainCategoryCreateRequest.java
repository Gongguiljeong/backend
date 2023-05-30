package com.gongguiljeong.domain.category.dto;


import com.gongguiljeong.domain.category.model.MainCategory;
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
