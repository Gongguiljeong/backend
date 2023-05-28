package com.gongguiljeong.domain.influencer.repository;

import com.gongguiljeong.domain.influencer.model.Influencer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfluncerRepository extends JpaRepository<Influencer, Long> {
}
