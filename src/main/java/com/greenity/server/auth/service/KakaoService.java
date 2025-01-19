package com.greenity.server.auth.service;

import com.greenity.server.user.dto.response.KakaoUserResponse;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class KakaoService {

    private final RestTemplate restTemplate;

    @Value("${kakao.api-key}")
    private String clientId;

    @Value("${kakao.redirect-uri}")
    private String redirectUri;

    @Value("${kakao.token-uri}")
    private String tokenUrl;

    @Value("${kakao.user-info-uri}")
    private String userInfoUrl;

    // 인가 코드를 이용해 토큰 발급
    public String getKakaoAccessToken(String code) throws Exception {
        String accessToken = "";
        String refreshToken = "";

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("grant_type", "authorization_code");
            params.add("client_id", clientId);
            params.add("redirect_uri", redirectUri);
            params.add("code", code);

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

            ResponseEntity<String> response = restTemplate.exchange(tokenUrl, HttpMethod.POST, request, String.class);

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(response.getBody());

            accessToken  = (String) jsonObj.get("access_token");
            refreshToken = (String) jsonObj.get("refresh_token");

        } catch (Exception e) {
            throw new Exception("API 호출 실패");
        }

        return accessToken;
    }

    // 발급받은 토큰으로 사용자 정보 요청
    public KakaoUserResponse getUserInfo(String accessToken) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<?> request = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(userInfoUrl, HttpMethod.POST, request, String.class);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObj    = (JSONObject) jsonParser.parse(response.getBody());
        JSONObject account = (JSONObject) jsonObj.get("kakao_account");
        JSONObject profile = (JSONObject) account.get("profile");

        Long id = (Long)jsonObj.get("id");
        String nickname = String.valueOf(profile.get("nickname"));
        String email = String.valueOf(account.get("email"));
        String profile_image = String.valueOf(profile.get("profile_image"));

        return KakaoUserResponse.builder()
                .id(id)
                .nickname(nickname)
                .email(email)
                .image(profile_image)
                .build();
    }

}