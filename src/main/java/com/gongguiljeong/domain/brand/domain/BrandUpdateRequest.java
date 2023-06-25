package com.gongguiljeong.domain.brand.domain;


import com.gongguiljeong.domain.common.domain.Status;
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
    private final Status used;

}
