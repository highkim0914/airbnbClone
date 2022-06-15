package com.tube.airbnb.dto;

import com.tube.airbnb.entity.embedded.BasicHouseProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class PostHouseReq {
    private BasicHouseProperty basicHouseProperty;
    private long hostId;
}
