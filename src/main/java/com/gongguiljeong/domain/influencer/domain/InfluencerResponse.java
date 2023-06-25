package com.gongguiljeong.domain.influencer.domain;


import com.gongguiljeong.domain.influencer.domain.Influencer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class InfluencerResponse {
    private final String name;
    private final String link;
    private final LocalDateTime createDate;
    private final LocalDateTime updateDate;

    public InfluencerResponse(Influencer influencer) {
        this.name = influencer.getName();
        this.link = influencer.getLink();
        this.createDate = influencer.getCreateDate();
        this.updateDate = influencer.getUpdateDate();
    }

}
