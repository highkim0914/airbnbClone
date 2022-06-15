package com.tube.airbnb.controller;

import com.tube.airbnb.config.BaseResponseStatus;
import com.tube.airbnb.exception.BaseException;
import com.tube.airbnb.service.KakaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.tube.airbnb.config.secret.Secret.KAKAO_CLIENT_ID;

@Controller
@RequestMapping("/kakao-login")
public class KakaoRestcontroller {

    @Autowired
    KakaoService kakaoService;

    @GetMapping()
    public String kakaoGetCode(){
        try {
            return "redirect:https://kauth.kakao.com/oauth/authorize?response_type=code&client_id="+ KAKAO_CLIENT_ID + "&redirect_uri=" + "http://localhost:9000/kakao-login/token";
        }
        catch (Exception e){
            e.printStackTrace();
            throw new BaseException(BaseResponseStatus.KAKAO_GET_CODE_ERROR);
        }
    }

    @GetMapping("/token")
    public void getToken(@RequestParam String code){
        System.out.println(code);
        kakaoService.getToken(code);
    }

}
