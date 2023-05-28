package com.gongguiljeong.domain.brand.dto;


import com.gongguiljeong.domain.brand.model.Brand;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BrandCreateRequest {


    @NotBlank
    private final String name;

    @NotBlank
    private final String link;

    public Brand toEntity() {
        return new Brand(name, link);
    }
}
