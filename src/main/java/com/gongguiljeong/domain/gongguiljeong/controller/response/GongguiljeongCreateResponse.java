package com.gongguiljeong.domain.gongguiljeong.controller.response;

import com.gongguiljeong.domain.gongguiljeong.domain.Gongguiljeong;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GongguiljeongCreateResponse {

    private final Long gongguiljeongId;

    @Builder
    public GongguiljeongCreateResponse(Long gongguiljeongId) {
        this.gongguiljeongId = gongguiljeongId;
    }

    public static GongguiljeongCreateResponse from(Gongguiljeong gongguiljeong) {
        return GongguiljeongCreateResponse.builder()
                .gongguiljeongId(gongguiljeong.getId())
                .build();
    }
}
