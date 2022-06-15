package com.tube.airbnb.service;

import com.tube.airbnb.dto.PostWishListReq;
import com.tube.airbnb.dto.PostWishListRes;
import com.tube.airbnb.exception.BaseException;
import com.tube.airbnb.config.BaseResponseStatus;
import com.tube.airbnb.entity.User;
import com.tube.airbnb.entity.WishList;
import com.tube.airbnb.repository.WishListRepository;
import com.tube.airbnb.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.tube.airbnb.config.BaseResponseStatus.DUPLICATED_WISHLIST_TITLE;

@Service
public class WishListService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private WishListRepository wishListRepository;

    @Autowired
    JwtService jwtService;

    @Autowired
    private UserService userService;


    public List<WishList> findAllByJwtToken() {
        return findAllByUserId(jwtService.getUserIdx());
    }

    private List<WishList> findAllByUserId(long userIdx) {
        return wishListRepository.findAllByUserId(userIdx);
    }

    public List<WishList> findAllById(long id) {
        return wishListRepository.findAllById(id);
    }

    public WishList findById(long wishListId) throws BaseException {
        return wishListRepository.findById(wishListId)
                .orElseThrow(()-> new BaseException(BaseResponseStatus.USERS_EMPTY_USER_ID));
    }

    public PostWishListRes createWishList(PostWishListReq postWishListReq) throws BaseException{
        User user = userService.findByUserId(postWishListReq.getUserId());
        String title = postWishListReq.getTitle();

        if(wishListRepository.findByIdAndTitle(user.getId(),title).isPresent()) {
            throw new BaseException(DUPLICATED_WISHLIST_TITLE);
        }
        WishList wishList = WishList.builder()
                .user(user)
                .title(title)
                .build();
        wishListRepository.save(wishList);
        return PostWishListRes.from(wishList);
    }

    @Transactional
    public void deleteWishList(long wishListId, long userId) throws BaseException{
        userService.checkJwtByUserId(userId);
        WishList wishList = findById(wishListId);
        wishList.setStatus(1);
        wishListRepository.save(wishList);
    }


    public WishList findByIdAndUserId(long wishListId, long userId) throws BaseException {
        return wishListRepository.findByIdAndUserId(wishListId, userId)
                .orElseThrow(()-> new BaseException(BaseResponseStatus.WISHLIST_WRONG_ID));
    }

}
