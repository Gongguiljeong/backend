package com.gongguiljeong.domain.gongguiljeong.controller.response;


import com.gongguiljeong.domain.image.domain.SubImage;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class SubImageResponse {

    private final String link;

    @Builder
    private SubImageResponse(String link) {
        this.link = link;
    }

    public static List<SubImageResponse> from(List<SubImage> subImages) {
        return subImages.stream()
                .map(subImage -> SubImageResponse.builder()
                        .link(subImage.getLink())
                        .build())
                .toList();
    }
}
