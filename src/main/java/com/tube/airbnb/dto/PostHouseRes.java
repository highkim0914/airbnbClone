package com.tube.airbnb.dto;

import com.tube.airbnb.entity.Host;
import com.tube.airbnb.entity.House;
import com.tube.airbnb.entity.embedded.BasicHouseProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.zip.CheckedOutputStream;

@Getter
@Setter
@NoArgsConstructor
public class PostHouseRes {
    private long id;
    private BasicHouseProperty basicHouseProperty;

    public static PostHouseRes from(House house){
        PostHouseRes postHouseRes = new PostHouseRes();
        postHouseRes.setId(house.getId());
        postHouseRes.setBasicHouseProperty(house.getBasicHouseProperty());
        return postHouseRes;

    }
}
