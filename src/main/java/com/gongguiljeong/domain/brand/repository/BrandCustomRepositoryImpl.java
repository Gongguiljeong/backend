package com.gongguiljeong.domain.brand.repository;


import com.gongguiljeong.domain.brand.dto.BrandResponse;
import com.gongguiljeong.domain.brand.dto.BrandSearchCondition;
import com.gongguiljeong.domain.brand.dto.QBrandResponse;
import com.gongguiljeong.global.base_model.Used;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.gongguiljeong.domain.brand.model.QBrand.brand;

@RequiredArgsConstructor
public class BrandCustomRepositoryImpl implements BrandCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<BrandResponse> getBrandList(BrandSearchCondition brandSearchCondition, Pageable pageable) {
        List<BrandResponse> result = jpaQueryFactory.select(new QBrandResponse(brand))
                .from(brand)
                .where(eqUsed(brandSearchCondition))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(brand.used.desc())
                .fetch();

        int size = jpaQueryFactory.select(new QBrandResponse(brand))
                .from(brand)
                .where(eqUsed(brandSearchCondition))
                .fetch().size();
        return new PageImpl<>(result, pageable, size);


    }

    private BooleanExpression eqUsed(BrandSearchCondition brandSearchCondition) {
        if (brandSearchCondition == null) {
            return null;
        }
        String used = brandSearchCondition.getUsed().toUpperCase();
        return brand.used.eq(Used.valueOf(used));
    }


}
