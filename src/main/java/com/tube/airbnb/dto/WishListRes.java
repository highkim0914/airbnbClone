package com.tube.airbnb.dto;

import com.tube.airbnb.entity.WishList;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WishListRes {
    private long id;
    private String title;

    public static WishListRes from(WishList wishList){
        WishListRes wishListRes = new WishListRes();
        wishListRes.setId(wishList.getId());
        wishListRes.setTitle(wishList.getTitle());
        return wishListRes;
    }
}
