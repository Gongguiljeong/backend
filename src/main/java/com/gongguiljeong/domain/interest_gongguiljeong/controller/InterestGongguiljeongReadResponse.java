package com.gongguiljeong.domain.interest_gongguiljeong.controller;

import com.gongguiljeong.domain.gongguiljeong.controller.response.GongguiljeongResponse;
import com.gongguiljeong.domain.interest_gongguiljeong.domain.InterestGongguiljeong;
import lombok.Builder;
import lombok.Getter;

@Getter
public class InterestGongguiljeongReadResponse {

    private final Long id;
    private final GongguiljeongResponse gongguiljeong;
    @Builder
    public InterestGongguiljeongReadResponse(Long id, GongguiljeongResponse gongguiljeong) {
        this.id = id;
        this.gongguiljeong = gongguiljeong;
    }

    public static InterestGongguiljeongReadResponse from(InterestGongguiljeong interestGongguiljeong) {
        return InterestGongguiljeongReadResponse.builder()
                .id(interestGongguiljeong.getId())
                .gongguiljeong(GongguiljeongResponse.from(interestGongguiljeong.getGongguiljeong()))
                .build();
    }
}
