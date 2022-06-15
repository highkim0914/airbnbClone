package com.tube.airbnb.controller;

import com.tube.airbnb.config.BaseResponse;
import com.tube.airbnb.config.BaseResponseStatus;
import com.tube.airbnb.dto.GetReservationRes;
import com.tube.airbnb.dto.PostReservationReq;
import com.tube.airbnb.dto.PostReservationRes;
import com.tube.airbnb.entity.Reservation;
import com.tube.airbnb.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationRestController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public BaseResponse<PostReservationRes> createReservation(@RequestBody PostReservationReq postReservationReq){
        PostReservationRes postReservationRes = reservationService.createReservation(postReservationReq);
        return new BaseResponse<>(postReservationRes);
    }

    @GetMapping("/{userId}")
    public BaseResponse<List<GetReservationRes>> getReservationsOfUser(@PathVariable long userId){
        List<GetReservationRes> getReservationResList = reservationService.getReservationsOfUser(userId);
        return new BaseResponse<>(getReservationResList);
    }

    @PostMapping("/{id}")
    public BaseResponse<BaseResponseStatus> cancelReservation(@PathVariable long id){
        reservationService.cancelReservation(id);
        return new BaseResponse<>(BaseResponseStatus.DELETE_SUCCESS);
    }

    @PostMapping("/{id}/{hostId}")
    public BaseResponse<BaseResponseStatus> approveReservation(@PathVariable long id, @PathVariable long hostId){
        reservationService.approveReservation(id, hostId);
        return new BaseResponse<>(BaseResponseStatus.UPDATE_SUCCESS);
    }
}
