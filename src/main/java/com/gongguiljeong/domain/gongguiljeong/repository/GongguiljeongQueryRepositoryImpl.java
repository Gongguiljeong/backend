package com.gongguiljeong.domain.gongguiljeong.repository;

import com.gongguiljeong.domain.admin.domain.QAdmin;
import com.gongguiljeong.domain.brand.domain.QBrand;
import com.gongguiljeong.domain.category.domain.QMainCategory;
import com.gongguiljeong.domain.category.domain.QSubCategory;
import com.gongguiljeong.domain.gongguiljeong.domain.Gongguiljeong;
import com.gongguiljeong.domain.gongguiljeong.domain.QGongguiljeong;
import com.gongguiljeong.domain.image.domain.QMainImage;
import com.gongguiljeong.domain.influencer.domain.QInfluencer;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.gongguiljeong.domain.admin.domain.QAdmin.admin;
import static com.gongguiljeong.domain.brand.domain.QBrand.brand;
import static com.gongguiljeong.domain.category.domain.QMainCategory.mainCategory;
import static com.gongguiljeong.domain.category.domain.QSubCategory.subCategory;
import static com.gongguiljeong.domain.gongguiljeong.domain.QGongguiljeong.gongguiljeong;
import static com.gongguiljeong.domain.image.domain.QMainImage.mainImage;
import static com.gongguiljeong.domain.influencer.domain.QInfluencer.influencer;


@RequiredArgsConstructor
public class GongguiljeongQueryRepositoryImpl implements GongguiljeongQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Gongguiljeong> findByMainCategoryIdAndSubCategoryId(Long mainCategoryId, Long subCategoryId, Pageable pageable) {
        List<Gongguiljeong> content = jpaQueryFactory.select(gongguiljeong)
                .from(gongguiljeong)
                .join(gongguiljeong.mainCategory, mainCategory).fetchJoin()
                .join(gongguiljeong.mainImage, mainImage).fetchJoin()
                .join(gongguiljeong.subCategory, subCategory).fetchJoin()
                .join(gongguiljeong.influencer, influencer).fetchJoin()
                .join(gongguiljeong.brand, brand).fetchJoin()
                .join(gongguiljeong.admin, admin).fetchJoin()
                .where(eqMainCategoryId(mainCategoryId), eqSubCategoryId(subCategoryId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(content,pageable,content.size());
    }

    private BooleanExpression eqMainCategoryId(Long mainCategoryId) {
        if (mainCategoryId == null) {
            return null;
        }
        return gongguiljeong.mainCategory.id.eq(mainCategoryId);

    }

    private BooleanExpression eqSubCategoryId(Long subCategoryId) {
        if (subCategoryId == null) {
            return null;
        }
        return gongguiljeong.subCategory.id.eq(subCategoryId);

    }
}
