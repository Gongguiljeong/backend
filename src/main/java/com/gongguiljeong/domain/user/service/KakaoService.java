package com.gongguiljeong.domain.user.service;

import com.gongguiljeong.domain.user.domain.KakaoProfile;
import com.gongguiljeong.domain.user.domain.KakaoToken;
import com.gongguiljeong.domain.user.repository.KakaoClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoService {

    private final KakaoClient client;

    @Value("${kakao.tokenUrl}")
    private String kakaoTokenUrl;

    @Value("${kakao.userInfoUrl}")
    private String kakaoUserInfoURl;

    @Value("${kakao.restApiKey}")
    private String restapiKey;

    @Value("${kakao.redirectUrl}")
    private String redirectUrl;

    public KakaoProfile getInfo(final String code) throws URISyntaxException {
        final KakaoToken token = getToken(code);
        log.debug("token = {}", token);
        return client.getInfo(new URI(kakaoUserInfoURl), token.getToken_type() + " " + token.getAccess_token());
    }

    private KakaoToken getToken(final String code) throws URISyntaxException {
        return client.getToken(new URI(kakaoTokenUrl), restapiKey, redirectUrl, code, "authorization_code");
    }
}