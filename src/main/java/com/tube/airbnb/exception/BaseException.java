package com.tube.airbnb.exception;

import com.tube.airbnb.config.BaseResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseException extends RuntimeException {
    private BaseResponseStatus status;
}
