package com.tube.airbnb.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Entity
public class WishHouse extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "wish_list_id",nullable = false,updatable = false)
    private WishList wishList;

    @ManyToOne
    @JoinColumn(name = "house_id",nullable = false,updatable = false)
    private House house;

}
