package com.gongguiljeong.domain.influencer.domain;


import com.gongguiljeong.domain.influencer.domain.Influencer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class InfluencerCreateRequest {
    private final String name;
    private final String link;

    public Influencer toEntity() {
        return new Influencer(name, link);
    }
}
