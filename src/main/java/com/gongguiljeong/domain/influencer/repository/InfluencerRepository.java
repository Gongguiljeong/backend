package com.gongguiljeong.domain.influencer.repository;

import com.gongguiljeong.domain.influencer.domain.Influencer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfluencerRepository extends JpaRepository<Influencer, Long> {
}
