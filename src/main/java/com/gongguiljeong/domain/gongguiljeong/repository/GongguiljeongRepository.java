package com.gongguiljeong.domain.gongguiljeong.repository;

import com.gongguiljeong.domain.gongguiljeong.domain.Gongguiljeong;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GongguiljeongRepository extends JpaRepository<Gongguiljeong, Long>, GongguiljeongQueryRepository {


    @EntityGraph(attributePaths = {"mainImage", "mainCategory", "subCategory", "influencer", "brand", "admin"})
    Optional<Gongguiljeong> findById(Long id);


    @EntityGraph(attributePaths = {"mainImage", "mainCategory", "subCategory", "influencer", "brand", "admin"})
    Page<Gongguiljeong> findByTitleContaining(String title, Pageable pageable);


}
