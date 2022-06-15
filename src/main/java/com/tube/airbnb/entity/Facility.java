package com.tube.airbnb.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Entity
public class Facility extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String facilityCategory;
    private String name;
    private String detail;

    @Builder.Default
    @OneToMany(mappedBy = "facility")
    private List<HouseFacility> houseFacilities = new ArrayList<>();
}
