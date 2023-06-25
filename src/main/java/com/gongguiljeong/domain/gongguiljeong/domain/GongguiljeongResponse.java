package com.gongguiljeong.domain.gongguiljeong.domain;

import com.gongguiljeong.domain.gongguiljeong.domain.Gongguiljeong;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class GongguiljeongResponse {

    private final Long id;
    private final String title;
    private final LocalDateTime openDate;
    private final LocalDateTime closeDate;
    private final int interestCount;
    private final String mainImageLink;
    private final String mainCategory;
    private final String subCategory;

    public GongguiljeongResponse(Gongguiljeong gongguiljeong) {
        this.id = gongguiljeong.getId();
        this.title = gongguiljeong.getTitle();
        this.openDate = gongguiljeong.getOpenDate();
        this.closeDate = gongguiljeong.getCloseDate();
        this.interestCount = gongguiljeong.getInterestCount();
        this.mainImageLink = gongguiljeong.getMainImage().getLink();
        this.mainCategory = gongguiljeong.getMainCategory().getName();
        this.subCategory = gongguiljeong.getSubCategory().getName();
    }
}
