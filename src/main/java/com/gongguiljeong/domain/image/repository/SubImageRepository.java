package com.gongguiljeong.domain.image.repository;

import com.gongguiljeong.domain.image.domain.SubImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubImageRepository extends JpaRepository<SubImage, Long> {
    List<SubImage> findByGongguiljeongId(Long id);
}
