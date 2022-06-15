package com.tube.airbnb.dto;

import com.tube.airbnb.entity.Host;
import com.tube.airbnb.entity.House;
import com.tube.airbnb.entity.embedded.BasicHouseProperty;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class GetHostRes {
    private String name;

    private String email;

    private String detail;

    private List<BasicHouseProperty> basicHouseProperties;

    public static GetHostRes from(Host host, List<House> houses){
        GetHostRes getHostRes = new GetHostRes();
        getHostRes.setDetail(host.getDetail());
        getHostRes.setName(host.getName());
        getHostRes.setEmail(host.getEmail());
        getHostRes.setBasicHouseProperties(houses.stream().map(House::getBasicHouseProperty).collect(Collectors.toList()));
        return getHostRes;
    }
}
