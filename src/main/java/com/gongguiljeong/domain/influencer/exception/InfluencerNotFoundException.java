package com.gongguiljeong.domain.influencer.exception;

public class InfluencerNotFoundException extends RuntimeException {

    private final static String MESSAGE = "존재하지 않는 인플루언서입니다.";

    public InfluencerNotFoundException() {
        super(MESSAGE);
    }


}
