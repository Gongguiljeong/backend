package com.gongguiljeong.domain.influencer.controller;


import com.gongguiljeong.domain.influencer.dto.InfluencerCreateRequest;
import com.gongguiljeong.domain.influencer.dto.InfluencerResponse;
import com.gongguiljeong.domain.influencer.model.Influencer;
import com.gongguiljeong.domain.influencer.service.InfluencerService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/influencers")
public class InfluencerController {
    private final InfluencerService influencerService;


    @PostMapping("")
    public ResponseEntity<?> createInfluencer(@Valid @RequestBody InfluencerCreateRequest influencerCreateRequest) {
        influencerService.createInfluencer(influencerCreateRequest);
        return ResponseEntity.ok("");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readInfluencer(@PathVariable Long id) {
        InfluencerResponse influencerResponse = influencerService.readInfluencer(id);
        return ResponseEntity.ok(influencerResponse);
    }

    @GetMapping("")
    public ResponseEntity<?> readInfluencerList(Pageable pageable) {
        Page<InfluencerResponse> influencerResponses = influencerService.readInfluencerList(pageable);
        return ResponseEntity.ok(influencerResponses);
    }
}
