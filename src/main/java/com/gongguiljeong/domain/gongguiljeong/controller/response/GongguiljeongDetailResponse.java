package com.gongguiljeong.domain.gongguiljeong.controller.response;


import com.gongguiljeong.domain.gongguiljeong.domain.Gongguiljeong;
import com.gongguiljeong.domain.image.domain.SubImage;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class GongguiljeongDetailResponse {

    private final Long id;
    private final String title;
    private final LocalDateTime openDate;
    private final LocalDateTime closeDate;
    private final int interestCount;
    private final String mainImageLink;
    private final String mainCategory;
    private final String subCategory;
    private final String brand;
    private final List<SubImageResponse> subImages;

    @Builder
    private GongguiljeongDetailResponse(Long id, String title, LocalDateTime openDate, LocalDateTime closeDate, int interestCount, String mainImageLink, String mainCategory, String subCategory, String brand, List<SubImageResponse> subImages) {
        this.id = id;
        this.title = title;
        this.openDate = openDate;
        this.closeDate = closeDate;
        this.interestCount = interestCount;
        this.mainImageLink = mainImageLink;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.brand = brand;
        this.subImages = subImages;
    }



    public static GongguiljeongDetailResponse from(Gongguiljeong gongguiljeong, List<SubImage> subImages) {
        return GongguiljeongDetailResponse.builder()
                .id(gongguiljeong.getId())
                .title(gongguiljeong.getTitle())
                .openDate(gongguiljeong.getOpenDate())
                .closeDate(gongguiljeong.getCloseDate())
                .interestCount(gongguiljeong.getInterestCount())
                .mainImageLink(gongguiljeong.getMainImage().getLink())
                .mainCategory(gongguiljeong.getMainCategory().getName())
                .subCategory(gongguiljeong.getSubCategory().getName())
                .brand(gongguiljeong.getBrand().getName())
                .subImages(SubImageResponse.from(subImages))
                .build();
    }
}
