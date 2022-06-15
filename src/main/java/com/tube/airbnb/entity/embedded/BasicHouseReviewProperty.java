package com.tube.airbnb.entity.embedded;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class BasicHouseReviewProperty {
    private String username;
    private String detail;

    private int eval_1;
    private int eval_2;
    private int eval_3;
    private int eval_4;
    private int eval_5;
    private int eval_6;

    private double averageStar;

    private String hostResponse;
    private LocalDate hostResponseDate;

}
