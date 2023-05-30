package com.gongguiljeong.domain.influencer.dto;


import com.gongguiljeong.domain.influencer.model.Influencer;
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
