package com.gongguiljeong.domain.admin.controller.response;

import com.gongguiljeong.domain.gongguiljeong.domain.Gongguiljeong;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GongguiljeongUpdateResponse {
    private final Long gongguiljeongId;

    @Builder
    private GongguiljeongUpdateResponse(Long gongguiljeongId) {
        this.gongguiljeongId = gongguiljeongId;
    }

    public static GongguiljeongUpdateResponse from(Gongguiljeong gongguiljeong) {
        return GongguiljeongUpdateResponse.builder()
                .gongguiljeongId(gongguiljeong.getId())
                .build();
    }
}
