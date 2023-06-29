package com.gongguiljeong.domain.gongguiljeong.domain;


import com.gongguiljeong.domain.admin.domain.Admin;
import com.gongguiljeong.domain.brand.domain.Brand;
import com.gongguiljeong.domain.category.domain.MainCategory;
import com.gongguiljeong.domain.category.domain.SubCategory;
import com.gongguiljeong.domain.image.domain.MainImage;
import com.gongguiljeong.domain.image.domain.SubImage;
import com.gongguiljeong.domain.influencer.domain.Influencer;
import com.gongguiljeong.domain.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "gongguiljeongs")
public class Gongguiljeong extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gongguiljeong_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_image_id", nullable = false)
    private MainImage mainImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_category_id", nullable = false)
    private MainCategory mainCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category_id", nullable = false)
    private SubCategory subCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "influencer_id", nullable = false)
    private Influencer influencer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;
    private String title;
    private String link;
    private LocalDateTime openDate;
    private LocalDateTime closeDate;
    private int interestCount;

    @Builder
    private Gongguiljeong(Long id, MainImage mainImage, MainCategory mainCategory, SubCategory subCategory, Influencer influencer, Brand brand, Admin admin, String title, String link, LocalDateTime openDate, LocalDateTime closeDate, int interestCount) {
        this.id = id;
        this.mainImage = mainImage;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.influencer = influencer;
        this.brand = brand;
        this.admin = admin;
        this.title = title;
        this.link = link;
        this.openDate = openDate;
        this.closeDate = closeDate;
        this.interestCount = interestCount;
    }
}
