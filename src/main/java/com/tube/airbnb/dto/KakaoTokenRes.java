package com.tube.airbnb.dto;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class KakaoTokenRes {
    private String tokenType;
    private String accessToken;
    private String idToken;
    private int expiresIn;
    private String refreshToken;
    private int refreshTokenExpiresIn;
    private String scope;

}
