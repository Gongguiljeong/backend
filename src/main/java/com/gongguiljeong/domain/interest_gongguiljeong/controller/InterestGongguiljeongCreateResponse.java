package com.gongguiljeong.domain.interest_gongguiljeong.controller;

import com.gongguiljeong.domain.interest_gongguiljeong.domain.InterestGongguiljeong;
import lombok.Builder;
import lombok.Getter;

@Getter
public class InterestGongguiljeongCreateResponse {
    private Long id;

    @Builder
    private InterestGongguiljeongCreateResponse(Long id) {
        this.id = id;
    }

    public static InterestGongguiljeongCreateResponse from(InterestGongguiljeong interestGongguiljeong) {
        return InterestGongguiljeongCreateResponse.builder()
                .id(interestGongguiljeong.getId())
                .build();
    }
}
