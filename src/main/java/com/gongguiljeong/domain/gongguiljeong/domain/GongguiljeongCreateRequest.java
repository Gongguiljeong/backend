package com.gongguiljeong.domain.gongguiljeong.domain;


import com.gongguiljeong.domain.admin.domain.Admin;
import com.gongguiljeong.domain.category.domain.MainCategory;
import com.gongguiljeong.domain.category.domain.SubCategory;
import com.gongguiljeong.domain.image.domain.MainImage;
import com.gongguiljeong.domain.influencer.domain.Influencer;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class GongguiljeongCreateRequest {

    @NotNull
    private final Long mainCategoryId;

    @NotNull
    private final Long subCategoryId;

    @NotNull
    private final Long influencerId;

    @NotNull
    private final String title;

    @NotNull
    private final String link;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private final LocalDateTime openDate;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private final LocalDateTime closeDate;

    public Gongguiljeong toEntity(Admin admin, Influencer influencer, MainCategory mainCategory, SubCategory subCategory, MainImage mainImage) {
        return new Gongguiljeong(mainImage, mainCategory, subCategory, influencer, admin, title, link, openDate, closeDate);
    }
}
