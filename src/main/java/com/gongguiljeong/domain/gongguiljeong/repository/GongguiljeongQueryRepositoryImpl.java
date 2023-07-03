package com.gongguiljeong.domain.gongguiljeong.repository;

import com.gongguiljeong.domain.gongguiljeong.domain.Gongguiljeong;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

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
        List<Gongguiljeong> content = jpaQueryFactory.selectFrom(gongguiljeong)
                .join(gongguiljeong.mainCategory, mainCategory).fetchJoin()
                .join(gongguiljeong.mainImage, mainImage).fetchJoin()
                .join(gongguiljeong.subCategory, subCategory).fetchJoin()
                .join(gongguiljeong.influencer, influencer).fetchJoin()
                .join(gongguiljeong.brand, brand).fetchJoin()
                .join(gongguiljeong.admin, admin).fetchJoin()
                .where(eqMainCategoryId(mainCategoryId), eqSubCategoryId(subCategoryId, mainCategoryId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = jpaQueryFactory.select(gongguiljeong.count())
                .from(gongguiljeong)
                .join(gongguiljeong.mainCategory, mainCategory).fetchJoin()
                .join(gongguiljeong.subCategory, subCategory).fetchJoin()
                .where(eqMainCategoryId(mainCategoryId), eqSubCategoryId(subCategoryId, mainCategoryId));
        //TODO : count 쿼리 따로 작성해야함

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private BooleanExpression eqMainCategoryId(Long mainCategoryId) {
        if (mainCategoryId == null) {
            return null;
        }
        return gongguiljeong.mainCategory.id.eq(mainCategoryId);

    }

    private BooleanExpression eqSubCategoryId(Long subCategoryId, Long mainCategoryId) {
        if (mainCategoryId == null) {
            return null;
        }
        if (subCategoryId == null) {
            return null;
        }
        return gongguiljeong.subCategory.id.eq(subCategoryId);
    }
}
