package com.tube.airbnb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchHostReq {
    private String email;
    private String detail;
}
