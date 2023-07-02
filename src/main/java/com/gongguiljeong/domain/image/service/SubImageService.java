package com.gongguiljeong.domain.image.service;


import com.gongguiljeong.domain.image.domain.SubImage;
import com.gongguiljeong.domain.image.repository.SubImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SubImageService {
    private final SubImageRepository subImageRepository;


    public List<SubImage> getByGonggiljeongId(Long id) {
        List<SubImage> subImages = subImageRepository.findByGongguiljeongId(id);
        for (SubImage subImage : subImages) {
            System.out.println("subImage = " + subImage.getName());
            System.out.println("subImage = " + subImage.getLink());

        }
        return subImages;
    }
}
