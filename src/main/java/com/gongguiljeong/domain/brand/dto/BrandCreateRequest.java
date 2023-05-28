package com.gongguiljeong.domain.brand.dto;


import com.gongguiljeong.domain.brand.model.Brand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BrandCreateRequest {

    
    private final String name;
    private final String link;

    public Brand toEntity() {
        return new Brand(name, link);
    }
}
