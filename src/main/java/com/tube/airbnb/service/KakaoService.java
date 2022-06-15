package com.tube.airbnb.service;

import com.tube.airbnb.config.Constant;
import com.tube.airbnb.dto.KakaoTokenRes;
import com.tube.airbnb.utils.KakaoResponse;
import io.jsonwebtoken.Jwts;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.http.*;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static com.tube.airbnb.config.Constant.KAKAO_CLIENT_ID;

@Service
public class KakaoService {
    public String getToken(String code) {
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "authorization_code");
        map.add("client_id", KAKAO_CLIENT_ID);
        map.add("redirect_uri","http://localhost:9000/kakao-login/token");
        map.add("code", code);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.valueOf("application/x-www-form-urlencoded"));

        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        URI uri = UriComponentsBuilder
                .fromUriString("https://kauth.kakao.com")
                .path("/oauth/token")
                .queryParams(map)
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<KakaoResponse> response = restTemplate.exchange(uri,HttpMethod.POST,httpEntity, KakaoResponse.class);

        System.out.println(response.getBody());

        return response.getBody().toString();
    }
}
