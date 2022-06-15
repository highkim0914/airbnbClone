package com.tube.airbnb.config;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum Gender{
    MALE,
    FEMALE,
    NONE
}
