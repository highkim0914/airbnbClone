package com.tube.airbnb.controller;

import com.tube.airbnb.dto.PostWishListRes;
import com.tube.airbnb.dto.WishListRes;
import com.tube.airbnb.exception.BaseException;
import com.tube.airbnb.config.BaseResponse;
import com.tube.airbnb.config.BaseResponseStatus;
import com.tube.airbnb.dto.PostWishListReq;
import com.tube.airbnb.entity.User;
import com.tube.airbnb.entity.WishHouse;
import com.tube.airbnb.entity.WishList;
import com.tube.airbnb.repository.UserRepository;
import com.tube.airbnb.service.UserService;
import com.tube.airbnb.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/wishlists")
public class WishListRestController {
    @Autowired
    WishListService wishListService;

    @Autowired
    UserService userService;

    @GetMapping
    public BaseResponse<List<WishListRes>> getWishListsOfUser(){
        List<WishListRes> wishListResList = wishListService.findAllByJwtToken().stream()
                .map(WishListRes::from)
                .collect(Collectors.toList());
        return new BaseResponse<>(wishListResList);
    }

    @PostMapping
    public BaseResponse<PostWishListRes> createWishList(@RequestBody PostWishListReq postWishListReq){
        PostWishListRes postWishListRes = wishListService.createWishList(postWishListReq);

        return new BaseResponse<>(postWishListRes);
    }

    @GetMapping("/{id}")
    public BaseResponse<List<WishHouse>> getWishHousesOfWishList(@PathVariable long id){
        WishList wishList = wishListService.findById(id);
        List<WishHouse> wishHouses = wishList.getWishHouses();
        return new BaseResponse<>(wishHouses);
    }

    @PostMapping("/{id}")
    public BaseResponse<WishList> deleteWishList(@PathVariable long id, @RequestBody long userId){

        User user = userService.findByUserId(userId);

        wishListService.deleteWishList(id, userId);

        return new BaseResponse<>(BaseResponseStatus.DELETE_SUCCESS);
    }
}
