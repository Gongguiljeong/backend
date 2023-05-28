package com.gongguiljeong.domain.gongguiljeong.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class GongguiljeongCreateRequest {
    private final Long mainCategoryId;
    private final Long subCategoryId;
    private final Long influencerId;
    private final Long adminId;
    private final String title;
    private final String link;
    private final LocalDateTime openDate;
    private final LocalDateTime closeDate;
}
