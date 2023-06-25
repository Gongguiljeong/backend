package com.gongguiljeong.domain.brand.repository;


import com.gongguiljeong.domain.brand.domain.BrandResponse;
import com.gongguiljeong.domain.brand.domain.BrandSearchCondition;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@RequiredArgsConstructor
public class BrandCustomRepositoryImpl implements BrandCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public Page<BrandResponse> getBrandList(BrandSearchCondition brandSearchCondition, Pageable pageable) {
        return null;
    }
}
