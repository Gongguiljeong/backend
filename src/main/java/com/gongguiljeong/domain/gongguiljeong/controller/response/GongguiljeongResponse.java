package com.gongguiljeong.domain.gongguiljeong.controller.response;

import com.gongguiljeong.domain.gongguiljeong.domain.Gongguiljeong;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GongguiljeongResponse {
    private final Long id;
    private final String title;
    private final LocalDateTime openDate;
    private final LocalDateTime closeDate;
    private final int interestCount;
    private final String mainImageLink;
    private final String mainCategory;
    private final String subCategory;


    @Builder
    private GongguiljeongResponse(Long id, String title, LocalDateTime openDate, LocalDateTime closeDate, int interestCount, String mainImageLink, String mainCategory, String subCategory) {
        this.id = id;
        this.title = title;
        this.openDate = openDate;
        this.closeDate = closeDate;
        this.interestCount = interestCount;
        this.mainImageLink = mainImageLink;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
    }

    public static GongguiljeongResponse  from(Gongguiljeong gongguiljeong) {
        return GongguiljeongResponse.builder()
                .id(gongguiljeong.getId())
                .title(gongguiljeong.getTitle())
                .openDate(gongguiljeong.getOpenDate())
                .closeDate(gongguiljeong.getCloseDate())
                .interestCount(gongguiljeong.getInterestCount())
                .mainImageLink(gongguiljeong.getMainImage().getLink())
                .mainCategory(gongguiljeong.getMainCategory().getName())
                .subCategory(gongguiljeong.getSubCategory().getName())
                .build();
    }
}
