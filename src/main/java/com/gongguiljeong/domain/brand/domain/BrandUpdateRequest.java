package com.gongguiljeong.domain.brand.domain;


import com.gongguiljeong.domain.common.domain.Used;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BrandUpdateRequest {
    @NotBlank
    private final String name;

    @NotBlank
    private final String link;

    @NotBlank
    private final Used used;

}