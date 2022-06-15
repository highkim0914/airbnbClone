package com.tube.airbnb.entity;

import com.tube.airbnb.entity.embedded.BasicHouseProperty;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Entity
@Where(clause = "status = 0")
public class House extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Embedded
    private BasicHouseProperty basicHouseProperty;

    @ManyToOne
    @JoinColumn(name="host_id", nullable = false, updatable = false)
    private Host host;

    @Builder.Default
    @OneToMany(mappedBy = "house")
    private List<Reservation> reservations = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "house")
    private List<HouseReview> houseReviews = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "house")
    private List<WishHouse> wishHouses = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "house")
    private List<HouseFacility> houseFacilities = new ArrayList<>();
}
