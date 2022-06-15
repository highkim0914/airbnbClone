package com.tube.airbnb.entity.embedded;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BasicHouseProperty {
    private String title;
    private String propertyType;
    private String houseType;
    private String privateType;
    private String location;

    private int guest;
    private int bed;
    private int bedroom;
    private int bathroom;
    private Boolean isPrivateBathroom;

    private String detail;
    private String bookingOption;

    private int price;
    private int cleaningFee;
    private int serviceFee;

    private String houseRule;
    private String hostRule;
    private String refundPolicy;
}
