package com.tube.airbnb.dto;

import com.tube.airbnb.entity.*;
import com.tube.airbnb.entity.embedded.BasicHouseProperty;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class GetHouseInfoRes {
    BasicHouseProperty basicHouseProperty;
    List<GetReservationRes> reservations;
    List<HouseReview> houseReviews;
    List<Facility> facilities;

    public static GetHouseInfoRes from(House house){
        List<GetReservationRes> reservations = house.getReservations().stream()
                .map(GetReservationRes::from)
                .collect(Collectors.toList());
        List<HouseReview> houseReviews = house.getHouseReviews();
        List<Facility> facilities = house.getHouseFacilities().stream()
                .map(HouseFacility::getFacility)
                .collect(Collectors.toList());

        GetHouseInfoRes getHouseInfoRes = new GetHouseInfoRes();
        getHouseInfoRes.setBasicHouseProperty(house.getBasicHouseProperty());
        getHouseInfoRes.setReservations(reservations);
        getHouseInfoRes.setHouseReviews(houseReviews);
        getHouseInfoRes.setFacilities(facilities);
        return getHouseInfoRes;
    }
}
