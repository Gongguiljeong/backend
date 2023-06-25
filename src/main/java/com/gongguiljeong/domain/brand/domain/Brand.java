package com.gongguiljeong.domain.brand.domain;

import com.gongguiljeong.domain.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Brand extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String link;

    public Brand(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public void update(BrandUpdateRequest brandUpdateRequest) {
        this.name = brandUpdateRequest.getName();
        this.link = brandUpdateRequest.getLink();
        this.used = brandUpdateRequest.getUsed();
    }
}

