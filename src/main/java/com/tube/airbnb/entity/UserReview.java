package com.tube.airbnb.entity;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Entity
public class UserReview extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String detail;
    private int score;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false,updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "host_id",nullable = false,updatable = false)
    private Host host;
}
