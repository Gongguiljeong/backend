package com.gongguiljeong.domain.gongguiljeong.service;


import com.gongguiljeong.domain.admin.domain.Admin;
import com.gongguiljeong.domain.category.domain.exception.MainCategoryNotFoundException;
import com.gongguiljeong.domain.category.domain.exception.SubCategoryNotFoundException;
import com.gongguiljeong.domain.category.domain.MainCategory;
import com.gongguiljeong.domain.category.domain.SubCategory;
import com.gongguiljeong.domain.category.repository.MainCategoryRepository;
import com.gongguiljeong.domain.category.repository.SubCategoryRepository;
import com.gongguiljeong.domain.admin.repository.AdminRepository;
import com.gongguiljeong.domain.gongguiljeong.domain.GongguiljeongCreateRequest;
import com.gongguiljeong.domain.gongguiljeong.repository.GongguiljeongRepository;
import com.gongguiljeong.domain.image.repository.MainImageRepository;
import com.gongguiljeong.domain.image.repository.SubImageRepository;
import com.gongguiljeong.domain.influencer.repository.InfluencerRepository;
import com.gongguiljeong.domain.gongguiljeong.domain.GongguiljeongResponse;
import com.gongguiljeong.domain.gongguiljeong.domain.exception.AdminNotFoundException;
import com.gongguiljeong.domain.gongguiljeong.domain.exception.GongguiljeongNotFoundException;
import com.gongguiljeong.domain.gongguiljeong.domain.Gongguiljeong;
import com.gongguiljeong.domain.image.domain.MainImage;
import com.gongguiljeong.domain.image.domain.SubImage;
import com.gongguiljeong.domain.influencer.exception.InfluencerNotFoundException;
import com.gongguiljeong.domain.influencer.model.Influencer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class GongguiljeongService {

    private final GongguiljeongRepository gongguiljeongRepository;
    private final AdminRepository adminRepository;
    private final InfluencerRepository influncerRepository;
    private final MainCategoryRepository mainCategoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final MainImageRepository mainImageRepository;
    private final SubImageRepository subImageRepository;
    private final static String FILEPATH = "C:\\Users\\rldh1\\OneDrive\\바탕 화면\\image\\";


    @Transactional
    public void createGongguiljeong(List<MultipartFile> files, GongguiljeongCreateRequest gongguiljeongCreateRequest) {

        //1. 유효성 검사
//        if (!admin.isUsed()) throw new AdminNotFoundException();
        Admin admin = adminRepository.findById(1L).orElseThrow(AdminNotFoundException::new);
//        MainCategory mainCategory = mainCategoryRepository.findById(1L).orElseThrow(MainCategoryNotFoundException::new);
//        SubCategory subCategory = subCategoryRepository.findById(1L).orElseThrow(SubCategoryNotFoundException::new);
//        Influencer influencer = influncerRepository.findById(1L).orElseThrow(InfluencerNotFoundException::new);
        Gongguiljeong gongguiljeong = null;


        Influencer influencer = influncerRepository.findById(gongguiljeongCreateRequest.getInfluencerId()).orElseThrow(InfluencerNotFoundException::new);
        if (!influencer.isUsed()) throw new InfluencerNotFoundException();

        MainCategory mainCategory = mainCategoryRepository.findById(gongguiljeongCreateRequest.getMainCategoryId()).orElseThrow(MainCategoryNotFoundException::new);
        if (!mainCategory.isUsed()) throw new MainCategoryNotFoundException();

        SubCategory subCategory = subCategoryRepository.findById(gongguiljeongCreateRequest.getSubCategoryId()).orElseThrow(SubCategoryNotFoundException::new);
        if (!subCategory.isUsed()) throw new SubCategoryNotFoundException();
        //2. 이미지 저장

        for (int i = 0; i < files.size(); i++) {
            System.out.println(files.get(i).getContentType());
            if (files.get(i).getContentType().startsWith("image")) {
                System.out.println("asdfsdf");
                String ext = files.get(i).getContentType().split("/")[1];
                String imageSaveName = UUID.randomUUID() + "." + ext;
                String path = FILEPATH + imageSaveName;
                try {
                    files.get(i).transferTo(new File(path));
                    if (i == 0) {
                        MainImage mainImage = mainImageRepository.save(new MainImage(imageSaveName, path));
                        gongguiljeong = gongguiljeongRepository.save(gongguiljeongCreateRequest.toEntity(admin, influencer, mainCategory, subCategory, mainImage));
                        log.info("mainImage : {}", mainImage);
                        log.info("gongguiljeong : {}", gongguiljeong);
                    }
                    subImageRepository.save(new SubImage(gongguiljeong, imageSaveName, path));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        //3. 공구일정 저장
//        gongguiljeongRepository.save(gongguiljeongCreateRequest.toEntity(admin, influencer, mainCategory, subCategory));

    }

    public GongguiljeongResponse readGongguiljeong(Long id) {
        Gongguiljeong gongguiljeong = gongguiljeongRepository.findById(id).orElseThrow(GongguiljeongNotFoundException::new);
        if (!gongguiljeong.isUsed()) throw new GongguiljeongNotFoundException();
        return new GongguiljeongResponse(gongguiljeong);

    }

    public Page<GongguiljeongResponse> readGongguiljeongList(Pageable pageable) {
        return gongguiljeongRepository.findAll(pageable).map(GongguiljeongResponse::new);

    }


    @Transactional
    public void deleteGongguiljeong(Long id) {
        Gongguiljeong gongguiljeong = gongguiljeongRepository.findById(id).orElseThrow(GongguiljeongNotFoundException::new);
        if (gongguiljeong.isUsed()) {
            gongguiljeong.delete();
        }
    }
}
