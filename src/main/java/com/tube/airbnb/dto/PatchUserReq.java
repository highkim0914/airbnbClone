package com.tube.airbnb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchUserReq {
    private String name;
    private String phoneNumber;
    private String address;
    private String emergencyContact;
}
