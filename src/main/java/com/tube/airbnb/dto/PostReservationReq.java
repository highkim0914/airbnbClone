package com.tube.airbnb.dto;

import com.tube.airbnb.entity.House;
import com.tube.airbnb.entity.Reservation;
import com.tube.airbnb.entity.User;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostReservationReq {
    private long userId;
    private long houseId;
    private LocalDate startDate;
    private int numberOfNights;
    private String message;

    public Reservation toEntity(User user, House house){
        return Reservation.builder()
                .user(user)
                .house(house)
                .startDate(startDate)
                .numberOfNights(numberOfNights)
                .message(message)
                .isApproved(false)
                .isFinished(false)
                .build();
    }
}
