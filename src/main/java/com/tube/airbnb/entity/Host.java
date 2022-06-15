package com.tube.airbnb.entity;
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
public class Host extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(mappedBy = "host")
    @ToString.Exclude
    private User user;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String detail;

    private Boolean isVerified;

    @Builder.Default
    @OneToMany(mappedBy = "host")
    private List<House> houses = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "host")
    private List<UserReview> userReviews = new ArrayList<>();
}
