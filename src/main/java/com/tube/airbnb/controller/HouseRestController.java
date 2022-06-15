package com.tube.airbnb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tube.airbnb.dto.*;
import com.tube.airbnb.entity.House;
import com.tube.airbnb.config.BaseResponse;
import com.tube.airbnb.config.BaseResponseStatus;
import com.tube.airbnb.service.HouseService;
import com.tube.airbnb.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.tube.airbnb.config.BaseResponseStatus.UPDATE_SUCCESS;

@RestController
@RequestMapping("/houses")
public class HouseRestController {
    @Autowired
    HouseService houseService;

    @PostMapping
    public BaseResponse<PostHouseRes> createHouse(@RequestBody PostHouseReq postHouseReq){
        PostHouseRes postHouseRes = houseService.createHouse(postHouseReq);
        return new BaseResponse<>(postHouseRes);
    }

    @GetMapping("/{id}")
    public BaseResponse<GetHouseInfoRes> getHouseInfo(@PathVariable long id){
        GetHouseInfoRes getHouseInfoRes= houseService.getHouseInfoById(id);
        return new BaseResponse<>(getHouseInfoRes);
    }

    @PostMapping("/{id}")
    public BaseResponse<BaseResponseStatus> deleteHouse(@PathVariable long id){
        houseService.deleteHouse(id);
        return new BaseResponse<>(BaseResponseStatus.DELETE_SUCCESS);
    }

    @PatchMapping("/{id}")
    public BaseResponse<BaseResponseStatus> updateHouse(@PathVariable long id, @RequestBody PatchHouseReq patchHouseReq){
        houseService.updateHouse(id, patchHouseReq);
        return new BaseResponse<>(UPDATE_SUCCESS);
    }

    @GetMapping
    public BaseResponse<Page<GetHouseInfoRes>> getHousesByPageable(Pageable pageable){
        Page<GetHouseInfoRes> housePage = houseService.findHousesByPageable(pageable);
        return new BaseResponse<>(housePage);
    }

}
