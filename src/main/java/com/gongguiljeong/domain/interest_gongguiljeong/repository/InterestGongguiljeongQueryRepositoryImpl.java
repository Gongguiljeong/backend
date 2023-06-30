package com.gongguiljeong.domain.interest_gongguiljeong.repository;

import com.gongguiljeong.domain.gongguiljeong.domain.QGongguiljeong;
import com.gongguiljeong.domain.interest_gongguiljeong.domain.InterestGongguiljeong;
import com.gongguiljeong.domain.interest_gongguiljeong.domain.QInterestGongguiljeong;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.gongguiljeong.domain.gongguiljeong.domain.QGongguiljeong.gongguiljeong;
import static com.gongguiljeong.domain.interest_gongguiljeong.domain.QInterestGongguiljeong.interestGongguiljeong;

@RequiredArgsConstructor
public class InterestGongguiljeongQueryRepositoryImpl implements InterestGongguiljeongQueryRepository{

    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public Page<InterestGongguiljeong> findByMainCategoryIdAndSubCategoryId(Pageable pageable, Long mainCategoryId, Long subCategoryId) {
        List<InterestGongguiljeong> content = jpaQueryFactory.selectFrom(interestGongguiljeong)
                .join(interestGongguiljeong.gongguiljeong, gongguiljeong)
                .where(eqMainCategoryId(mainCategoryId), eqMainCategoryId(subCategoryId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        //TODO : count 쿼리 따로 작성해야함
        return new PageImpl<>(content, pageable, content.size());
    }

    private BooleanExpression eqMainCategoryId(Long mainCategoryId) {
        if (mainCategoryId == null) {
            return null;
        }
        return interestGongguiljeong.gongguiljeong.mainCategory.id.eq(mainCategoryId);

    }

    private BooleanExpression eqSubCategoryId(Long subCategoryId) {
        if (subCategoryId == null) {
            return null;
        }
        return interestGongguiljeong.gongguiljeong.subCategory.id.eq(subCategoryId);
    }
}
