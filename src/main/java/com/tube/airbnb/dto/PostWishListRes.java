package com.tube.airbnb.dto;

import com.tube.airbnb.entity.WishList;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostWishListRes {
    private long id;
    private String title;

    public static PostWishListRes from(WishList wishList){
        PostWishListRes postWishListRes = new PostWishListRes();
        postWishListRes.setId(wishList.getId());
        postWishListRes.setTitle(wishList.getTitle());
        return postWishListRes;
    }
}
