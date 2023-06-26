package com.gongguiljeong.domain.gongguiljeong.repository;

import com.gongguiljeong.domain.gongguiljeong.domain.Gongguiljeong;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GongguiljeongRepository extends JpaRepository<Gongguiljeong, Long> {


    @EntityGraph(attributePaths = {"mainImage", "mainCategory", "subCategory", "influencer", "brand", "admin"})
    Optional<Gongguiljeong> findGongguiljeongById(Long id);


}
