package com.tube.airbnb.repository;

import com.tube.airbnb.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WishListRepository extends JpaRepository<WishList,Long> {
    List<WishList> findAllById(long id);
    Optional<WishList> findByIdAndTitle(long id, String title);

    Optional<WishList> findByIdAndUserId(long wishListId, long userId);

    List<WishList> findAllByUserId(long userIdx);
}
