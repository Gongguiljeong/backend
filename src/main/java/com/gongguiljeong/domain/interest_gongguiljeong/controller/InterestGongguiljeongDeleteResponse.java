package com.gongguiljeong.domain.interest_gongguiljeong.controller;

import com.gongguiljeong.domain.interest_gongguiljeong.domain.InterestGongguiljeong;
import lombok.Builder;
import lombok.Getter;

@Getter

public class InterestGongguiljeongDeleteResponse {
    private Long id;

    @Builder
    private InterestGongguiljeongDeleteResponse(Long id) {
        this.id = id;
    }

    public static InterestGongguiljeongCreateResponse from(InterestGongguiljeong interestGongguiljeong) {
        return InterestGongguiljeongCreateResponse.builder()
                .id(interestGongguiljeong.getId())
                .build();
    }
}
