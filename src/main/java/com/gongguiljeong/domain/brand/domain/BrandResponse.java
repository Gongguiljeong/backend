package com.gongguiljeong.domain.brand.domain;


import com.gongguiljeong.domain.common.domain.Used;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class BrandResponse {
    private final Long id;
    private final String name;
    private final String link;
    private final Used used;
    private final LocalDateTime createdDate;
    private final LocalDateTime updateDate;


    @QueryProjection
    public BrandResponse(Brand brand) {
        this.id = brand.getId();
        this.name = brand.getName();
        this.link = brand.getLink();
        this.used = brand.getUsed();
        this.createdDate = brand.getCreateDate();
        this.updateDate = brand.getUpdateDate();
    }
}
