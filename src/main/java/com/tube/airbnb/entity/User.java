package com.tube.airbnb.entity;

import com.tube.airbnb.config.Gender;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Entity
@Where(clause = "status = 0")
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    private Gender gender;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    private String phoneNumber;

    private String address;

    private String emergencyContact;

    @OneToOne
    @ToString.Exclude
    private Host host;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<HouseReview> houseReviews = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<UserReview> userReviews = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<WishList> wishLists = new ArrayList<>();
}
