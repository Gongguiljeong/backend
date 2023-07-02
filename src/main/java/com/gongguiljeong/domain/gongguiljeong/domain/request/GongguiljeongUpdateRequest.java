package com.gongguiljeong.domain.gongguiljeong.domain.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
public class GongguiljeongUpdateRequest {



    private final Long mainCategoryId;

    private final Long subCategoryId;

    private final Long brandId;

    private final Long influencerId;

    private final String title;

    private final String link;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private final LocalDateTime openDate;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private final LocalDateTime closeDate;

    @Builder

    public GongguiljeongUpdateRequest(Long mainCategoryId, Long subCategoryId, Long brandId, Long influencerId, String title, String link, LocalDateTime openDate, LocalDateTime closeDate) {
        this.mainCategoryId = mainCategoryId;
        this.subCategoryId = subCategoryId;
        this.brandId = brandId;
        this.influencerId = influencerId;
        this.title = title;
        this.link = link;
        this.openDate = openDate;
        this.closeDate = closeDate;
    }
}
