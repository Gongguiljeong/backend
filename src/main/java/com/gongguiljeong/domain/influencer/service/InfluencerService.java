package com.gongguiljeong.domain.influencer.service;


import com.gongguiljeong.domain.influencer.dto.InfluencerResponse;
import com.gongguiljeong.domain.influencer.exception.InfluencerNotFoundException;
import com.gongguiljeong.domain.influencer.model.Influencer;
import com.gongguiljeong.domain.influencer.repository.InfluencerRepository;
import com.gongguiljeong.domain.influencer.dto.InfluencerCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InfluencerService {
    private final InfluencerRepository influencerRepository;


    @Transactional
    public void createInfluencer(InfluencerCreateRequest influencerCreateRequest) {
        influencerRepository.save(influencerCreateRequest.toEntity());
    }

    public InfluencerResponse readInfluencer(Long id) {
        Influencer influencer = influencerRepository.findById(id).orElseThrow(InfluencerNotFoundException::new);
        return new InfluencerResponse(influencer);
    }

    public Page<InfluencerResponse> readInfluencerList(Pageable pageable) {
        return influencerRepository.findAll(pageable).map(InfluencerResponse::new);
    }
}
