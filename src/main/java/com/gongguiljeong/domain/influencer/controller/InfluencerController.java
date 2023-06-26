package com.gongguiljeong.domain.influencer.controller;


import com.gongguiljeong.domain.influencer.domain.InfluencerResponse;
import com.gongguiljeong.domain.influencer.domain.InfluencerCreateRequest;
import com.gongguiljeong.domain.influencer.service.InfluencerService;
import jakarta.validation.Valid;
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
    public ResponseEntity<?> create(@Valid @RequestBody InfluencerCreateRequest influencerCreateRequest) {
        influencerService.create(influencerCreateRequest);
        return ResponseEntity.ok("");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable Long id) {
        InfluencerResponse influencerResponse = influencerService.read(id);
        return ResponseEntity.ok(influencerResponse);
    }

    @GetMapping("")
    public ResponseEntity<?> readList(Pageable pageable) {
        Page<InfluencerResponse> influencerResponses = influencerService.readList(pageable);
        return ResponseEntity.ok(influencerResponses);
    }
}
