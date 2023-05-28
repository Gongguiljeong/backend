package com.gongguiljeong.domain.gongguiljeong.model;


import com.gongguiljeong.domain.admin.model.Admin;
import com.gongguiljeong.domain.category.model.MainCategory;
import com.gongguiljeong.domain.category.model.SubCategory;
import com.gongguiljeong.domain.image.model.MainImage;
import com.gongguiljeong.domain.influencer.model.Influencer;
import com.gongguiljeong.global.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Gongguiljeong extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gongguiljeong_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_image_id", nullable = false)
    private MainImage mainImage;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_category_id", nullable = false)
    private MainCategory mainCategory;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category_id", nullable = false)
    private SubCategory subCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "influencer_id", nullable = false)
    private Influencer influencer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;


    private String title;
    private String link;
    private LocalDateTime openDate;
    private LocalDateTime closeDate;
    private int interestCount;
}
