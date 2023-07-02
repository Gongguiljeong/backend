package com.gongguiljeong.domain.gongguiljeong.domain.request;


import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
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

    @Builder
    private GongguiljeongCreateRequest(Long mainCategoryId, Long subCategoryId, Long influencerId, String title, String link, LocalDateTime openDate, LocalDateTime closeDate) {
        this.mainCategoryId = mainCategoryId;
        this.subCategoryId = subCategoryId;
        this.influencerId = influencerId;
        this.title = title;
        this.link = link;
        this.openDate = openDate;
        this.closeDate = closeDate;
    }
}
