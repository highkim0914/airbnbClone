package com.tube.airbnb.dto;

import com.tube.airbnb.entity.*;
import com.tube.airbnb.entity.embedded.BasicHouseProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class GetHouseRes {
    private long id;
    private BasicHouseProperty basicHouseProperty;
    private Host host;
    private List<HouseReview> houseReviews;
    private List<HouseFacility> houseFacilities;

    public static GetHouseRes from(House house){
        return GetHouseRes.builder()
                .id(house.getId())
                .host(house.getHost())
                .basicHouseProperty(house.getBasicHouseProperty())
                .houseReviews(house.getHouseReviews())
                .houseFacilities(house.getHouseFacilities())
                .build();
    }
}
