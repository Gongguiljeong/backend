package com.gongguiljeong.domain.gongguiljeong.dto;


import com.gongguiljeong.domain.admin.model.Admin;
import com.gongguiljeong.domain.category.model.MainCategory;
import com.gongguiljeong.domain.category.model.SubCategory;
import com.gongguiljeong.domain.gongguiljeong.model.Gongguiljeong;
import com.gongguiljeong.domain.image.model.MainImage;
import com.gongguiljeong.domain.influencer.model.Influencer;
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
