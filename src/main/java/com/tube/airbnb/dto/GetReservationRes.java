package com.tube.airbnb.dto;

import com.tube.airbnb.entity.Reservation;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class GetReservationRes {
    private long id;
    private long houseId;
    private String houseTitle;
    private String refundPolicy;
    private LocalDate startDate;
    private int numberOfNights;
    private String message;
    private Boolean isApproved;
    private Boolean isFinished;

    public static GetReservationRes from(Reservation reservation){
        GetReservationRes getReservationRes = new GetReservationRes();
        getReservationRes.setId(reservation.getId());
        getReservationRes.setHouseId(reservation.getHouse().getId());
        getReservationRes.setHouseTitle(reservation.getHouse().getBasicHouseProperty().getTitle());
        getReservationRes.setRefundPolicy(reservation.getHouse().getBasicHouseProperty().getRefundPolicy());
        getReservationRes.setStartDate(reservation.getStartDate());
        getReservationRes.setNumberOfNights(reservation.getNumberOfNights());
        getReservationRes.setMessage(reservation.getMessage());
        getReservationRes.setIsApproved(reservation.getIsApproved());
        getReservationRes.setIsFinished(reservation.getIsFinished());
        return getReservationRes;
    }
}
