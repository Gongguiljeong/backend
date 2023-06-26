package com.gongguiljeong.domain.brand.repository;

import com.gongguiljeong.domain.brand.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    Optional<Brand> findByUsername(String username);

}
