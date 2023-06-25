package com.gongguiljeong.domain.brand.domain;


import com.gongguiljeong.domain.common.domain.Status;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BrandResponse {
    private final Long id;
    private final String name;
    private final String username;
    private final String password;
    private final String email;
    private final String link;
    private final Status status;
    private final LocalDateTime createdDate;
    private final LocalDateTime updateDate;


    @Builder
    private BrandResponse(Long id, String name, String username, String password, String email, String link, Status status, LocalDateTime createdDate, LocalDateTime updateDate) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.link = link;
        this.status = status;
        this.createdDate = createdDate;
        this.updateDate = updateDate;
    }


    public static BrandResponse from(Brand brand) {
        return BrandResponse.builder()
                .id(brand.getId())
                .name(brand.getName())
                .username(brand.getUsername())
                .password(brand.getPassword())
                .email(brand.getEmail())
                .link(brand.getLink())
                .status(brand.getStatus())
                .createdDate(brand.getCreateDate())
                .updateDate(brand.getUpdateDate())
                .build();
    }
}
