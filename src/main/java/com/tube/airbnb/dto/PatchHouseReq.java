package com.tube.airbnb.dto;

import com.tube.airbnb.entity.embedded.BasicHouseProperty;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchHouseReq {
    private BasicHouseProperty basicHouseProperty;
    private long hostId;
}
