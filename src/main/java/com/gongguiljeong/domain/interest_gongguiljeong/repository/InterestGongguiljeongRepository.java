package com.gongguiljeong.domain.interest_gongguiljeong.repository;

import com.gongguiljeong.domain.interest_gongguiljeong.domain.InterestGongguiljeong;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InterestGongguiljeongRepository extends JpaRepository<InterestGongguiljeong, Long>, InterestGongguiljeongQueryRepository {
    Optional<InterestGongguiljeong> findByIdAndUserId(Long interestId, Long userId);

    Optional<InterestGongguiljeong> findByGongguiljeongId(Long gongguiljeongId);


    @EntityGraph(attributePaths = {"gongguiljeong"})
    Page<InterestGongguiljeong> findByUserId(Long userId, Pageable pageable);


}
