package com.tube.airbnb.controller;

import com.tube.airbnb.exception.BaseException;
import com.tube.airbnb.config.BaseResponse;
import com.tube.airbnb.config.BaseResponseStatus;
import com.tube.airbnb.dto.*;
import com.tube.airbnb.service.UserService;
import com.tube.airbnb.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.tube.airbnb.config.BaseResponseStatus.*;
import static com.tube.airbnb.utils.ValidationRegex.isRegexEmail;

@RestController
public class UserRestController {
    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;

    @PostMapping("/users")
    public BaseResponse<PostUserRes> createUser(@RequestBody PostUserReq postUserReq){
        if(postUserReq.getEmail() == null){
            return new BaseResponse<>(POST_USERS_EMPTY_EMAIL);
        }
        if(!isRegexEmail(postUserReq.getEmail())) {
            return new BaseResponse<>(POST_USERS_INVALID_EMAIL);
        }

        PostUserRes postUserRes = userService.createUser(postUserReq);
        return new BaseResponse<>(postUserRes);

    }

    @GetMapping("/users/{id}")
    public BaseResponse<GetUserRes> getUser(@PathVariable long id){

        GetUserRes getUserRes = userService.getUser(id);
        return new BaseResponse<>(getUserRes);
    }

    @PostMapping("/users/{id}")
    public BaseResponse<BaseResponseStatus> deleteUser(@PathVariable long id){

        userService.deleteById(id);
        return new BaseResponse<>(BaseResponseStatus.DELETE_SUCCESS);
    }

    @PatchMapping("/users/id")
    public BaseResponse<BaseResponseStatus> updateUser(@PathVariable long id, @RequestBody PatchUserReq patchUserReq){
        userService.updateUser(id, patchUserReq);
        return new BaseResponse<>(BaseResponseStatus.UPDATE_SUCCESS);
    }


    @PostMapping("/logIn")
    public BaseResponse<PostLoginRes> logIn(@RequestBody PostLoginReq postLoginReq){
        if(postLoginReq.getEmail() == null){
            return new BaseResponse<>(POST_USERS_EMPTY_EMAIL);
        }
        if(!isRegexEmail(postLoginReq.getEmail())) {
            return new BaseResponse<>(POST_USERS_INVALID_EMAIL);
        }

        PostLoginRes postLoginRes = userService.logIn(postLoginReq);
        return new BaseResponse<>(postLoginRes);
    }



}
