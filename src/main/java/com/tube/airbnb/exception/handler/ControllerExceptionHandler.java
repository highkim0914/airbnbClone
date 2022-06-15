package com.tube.airbnb.exception.handler;

import com.tube.airbnb.config.BaseResponse;
import com.tube.airbnb.config.BaseResponseStatus;
import com.tube.airbnb.controller.UserRestController;
import com.tube.airbnb.exception.BaseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public BaseResponse<BaseResponseStatus> baseExceptionHandler(BaseException e){
        return new BaseResponse<>(e.getStatus());
    }
}
