package com.tube.airbnb.entity;

import com.tube.airbnb.entity.embedded.BasicHouseReviewProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Entity
public class HouseReview extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Embedded
    private BasicHouseReviewProperty basicHouseReviewProperty;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name="house_id",nullable = false,updatable = false)
    private House house;

}
