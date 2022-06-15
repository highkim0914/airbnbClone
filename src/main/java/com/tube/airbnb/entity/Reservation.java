package com.tube.airbnb.entity;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Entity
@Where(clause = "status = 0")
public class Reservation extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false,updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "house_id",nullable = false,updatable = false)
    private House house;

    private LocalDate startDate;

    private int numberOfNights;

    private String message;

    private Boolean isApproved;

    private Boolean isFinished;
}
