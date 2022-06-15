package com.tube.airbnb.dto;

import com.tube.airbnb.entity.House;
import com.tube.airbnb.entity.Reservation;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class PostReservationRes {
    private long id;
    private long houseId;
    private LocalDate startDate;
    private int numberOfNights;
    private String message;
    private Boolean isApproved;
    private Boolean isFinished;

    public static PostReservationRes from(Reservation reservation){
        PostReservationRes postReservationRes = new PostReservationRes();
        postReservationRes.setId(reservation.getId());
        postReservationRes.setHouseId(reservation.getHouse().getId());
        postReservationRes.setStartDate(reservation.getStartDate());
        postReservationRes.setNumberOfNights(reservation.getNumberOfNights());
        postReservationRes.setMessage(reservation.getMessage());
        postReservationRes.setIsApproved(reservation.getIsApproved());
        postReservationRes.setIsFinished(reservation.getIsFinished());
        return postReservationRes;
    }
}
