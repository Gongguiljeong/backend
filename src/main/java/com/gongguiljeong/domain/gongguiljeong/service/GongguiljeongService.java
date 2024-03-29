package com.gongguiljeong.domain.gongguiljeong.service;


import com.gongguiljeong.domain.admin.domain.Admin;
import com.gongguiljeong.domain.admin.repository.AdminRepository;
import com.gongguiljeong.domain.brand.domain.Brand;
import com.gongguiljeong.domain.brand.repository.BrandRepository;
import com.gongguiljeong.domain.category.domain.MainCategory;
import com.gongguiljeong.domain.category.domain.SubCategory;
import com.gongguiljeong.domain.category.repository.MainCategoryRepository;
import com.gongguiljeong.domain.category.repository.SubCategoryRepository;
import com.gongguiljeong.domain.common.domain.exception.GongguiljeongException;
import com.gongguiljeong.domain.gongguiljeong.domain.Gongguiljeong;
import com.gongguiljeong.domain.gongguiljeong.domain.request.GongguiljeongCreateRequest;
import com.gongguiljeong.domain.gongguiljeong.domain.request.GongguiljeongUpdateRequest;
import com.gongguiljeong.domain.gongguiljeong.repository.GongguiljeongRepository;
import com.gongguiljeong.domain.image.domain.MainImage;
import com.gongguiljeong.domain.image.domain.SubImage;
import com.gongguiljeong.domain.image.repository.MainImageRepository;
import com.gongguiljeong.domain.image.repository.SubImageRepository;
import com.gongguiljeong.domain.influencer.domain.Influencer;
import com.gongguiljeong.domain.influencer.repository.InfluencerRepository;
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
import java.util.Objects;
import java.util.UUID;

import static com.gongguiljeong.domain.common.domain.Status.Y;
import static com.gongguiljeong.domain.common.domain.exception.ExceptionCode.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class GongguiljeongService {

    private final GongguiljeongRepository gongguiljeongRepository;
    private final AdminRepository adminRepository;
    private final InfluencerRepository influencerRepository;
    private final MainCategoryRepository mainCategoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final MainImageRepository mainImageRepository;
    private final SubImageRepository subImageRepository;
    private final BrandRepository brandRepository;
    private final static String FILEPATH = "C:\\Users\\rldh1\\OneDrive\\바탕 화면\\image\\";


    @Transactional
    public Gongguiljeong create(List<MultipartFile> files, GongguiljeongCreateRequest gongguiljeongCreateRequest, Long id) {
        Admin admin = adminRepository.findById(id).orElseThrow(() -> new GongguiljeongException(ADMIN_NOT_FOUND));
        Gongguiljeong gongguiljeong = null;
        Influencer influencer = influencerRepository.findById(gongguiljeongCreateRequest.getInfluencerId()).orElseThrow(() -> new GongguiljeongException(INFLUENCER_NOT_FOUND));
        MainCategory mainCategory = mainCategoryRepository.findById(gongguiljeongCreateRequest.getMainCategoryId()).orElseThrow(() -> new GongguiljeongException(MAIN_CATEGORY_NOT_FOUND));
        SubCategory subCategory = subCategoryRepository.findById(gongguiljeongCreateRequest.getSubCategoryId()).orElseThrow(() -> new GongguiljeongException(SUB_CATEGORY_NOT_FOUND));
        //2. 이미지 저장

        for (int i = 0; i < files.size(); i++) {
            log.debug("file content-type: {}", files.get(i).getContentType());
            if (Objects.requireNonNull(files.get(i).getContentType()).startsWith("image")) {
                String ext = Objects.requireNonNull(files.get(i).getContentType()).split("/")[1];
                String imageSaveName = UUID.randomUUID() + "." + ext;
                String path = FILEPATH + imageSaveName;
                try {
                    files.get(i).transferTo(new File(path));
                    if (i == 0) {
                        MainImage mainImage = mainImageRepository.save(new MainImage(imageSaveName, path));
                        gongguiljeong = gongguiljeongRepository.save(Gongguiljeong.from(admin, influencer, mainCategory, subCategory, mainImage, gongguiljeongCreateRequest));
                        log.debug("메인이미지 : {}", mainImage);
                        log.debug("공구일정 저장 : {}", gongguiljeong);
                    }
                    subImageRepository.save(new SubImage(gongguiljeong, imageSaveName, path));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return gongguiljeong;
    }

    public Gongguiljeong get(Long id) {
        return gongguiljeongRepository.findById(id).orElseThrow(() -> new GongguiljeongException(GONGGUILJEONG_NOT_FOUND));
    }

    public Page<Gongguiljeong> getList(Pageable pageable) {
        return gongguiljeongRepository.findAll(pageable);
    }

    @Transactional
    public void delete(Long id) {
        Gongguiljeong gongguiljeong = gongguiljeongRepository.findById(id).orElseThrow(() -> new GongguiljeongException(GONGGUILJEONG_NOT_FOUND));
        if (gongguiljeong.status().equals(Y)) {
            gongguiljeong.delete();
        }
    }

    public Page<Gongguiljeong> findByTitle(Pageable pageable, String title) {
        return gongguiljeongRepository.findByTitleContaining(title, pageable);
    }

    public Page<Gongguiljeong> findByCategoryId(Pageable pageable, Long mainCategoryId, Long subCategoryId) {
        return gongguiljeongRepository.findByMainCategoryIdAndSubCategoryId(mainCategoryId, subCategoryId, pageable);
    }

    @Transactional
    public Gongguiljeong update(GongguiljeongUpdateRequest gongguiljeongUpdateRequest, Long id) {
        Gongguiljeong gongguiljeong = gongguiljeongRepository.findById(id).orElseThrow(() -> new GongguiljeongException(GONGGUILJEONG_NOT_FOUND));
        Influencer influencer = influencerRepository.findById(gongguiljeongUpdateRequest.getInfluencerId()).orElseThrow(() -> new GongguiljeongException(INFLUENCER_NOT_FOUND));
        MainCategory mainCategory = mainCategoryRepository.findById(gongguiljeongUpdateRequest.getMainCategoryId()).orElseThrow(() -> new GongguiljeongException(MAIN_CATEGORY_NOT_FOUND));
        SubCategory subCategory = subCategoryRepository.findById(gongguiljeongUpdateRequest.getSubCategoryId()).orElseThrow(() -> new GongguiljeongException(SUB_CATEGORY_NOT_FOUND));
        Brand brand = brandRepository.findById(gongguiljeongUpdateRequest.getBrandId()).orElseThrow(() -> new GongguiljeongException(BRAND_NOT_FOUND));
        gongguiljeong.update(gongguiljeongUpdateRequest, influencer, mainCategory, subCategory, brand);
        return gongguiljeong;
    }
}
