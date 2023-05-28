package com.gongguiljeong.domain.gongguiljeong.service;


import com.gongguiljeong.domain.admin.repository.AdminRepository;
import com.gongguiljeong.domain.category.exception.MainCategoryNotFoundException;
import com.gongguiljeong.domain.category.exception.SubCategoryNotFoundException;
import com.gongguiljeong.domain.category.model.MainCategory;
import com.gongguiljeong.domain.category.model.SubCategory;
import com.gongguiljeong.domain.category.repository.MainCategoryRepository;
import com.gongguiljeong.domain.category.repository.SubCategoryRepository;
import com.gongguiljeong.domain.gongguiljeong.dto.GongguiljeongCreateRequest;
import com.gongguiljeong.domain.gongguiljeong.repository.GongguiljeongRepository;
import com.gongguiljeong.domain.image.repository.MainImageRepository;
import com.gongguiljeong.domain.image.repository.SubImageRepository;
import com.gongguiljeong.domain.influencer.exception.InfluencerNotFoundException;
import com.gongguiljeong.domain.influencer.model.Influencer;
import com.gongguiljeong.domain.influencer.repository.InfluncerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GongguiljeongService {

    private final GongguiljeongRepository gongguiljeongRepository;
    private final AdminRepository adminRepository;
    private final InfluncerRepository influncerRepository;
    private final MainCategoryRepository mainCategoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final MainImageRepository mainImageRepository;
    private final SubImageRepository subImageRepository;


    public void createGongguiljeong(List<MultipartFile> files, GongguiljeongCreateRequest gongguiljeongCreateRequest) {
        files.stream().forEach(file -> {
            System.out.println(file.getOriginalFilename());
        });
        //1. 유효성 검사
//        if (!admin.isUsed()) throw new AdminNotFoundException();

        Influencer influencer = influncerRepository.findById(gongguiljeongCreateRequest.getInfluencerId()).orElseThrow(InfluencerNotFoundException::new);
        if (!influencer.isUsed()) throw new InfluencerNotFoundException();

        MainCategory mainCategory = mainCategoryRepository.findById(gongguiljeongCreateRequest.getMainCategoryId()).orElseThrow(MainCategoryNotFoundException::new);
        if (!mainCategory.isUsed()) throw new MainCategoryNotFoundException();

        SubCategory subCategory = subCategoryRepository.findById(gongguiljeongCreateRequest.getSubCategoryId()).orElseThrow(SubCategoryNotFoundException::new);
        if (!subCategory.isUsed()) throw new SubCategoryNotFoundException();

        //2. 이미지 저장


        //3. 공구일정 저장

    }
}
