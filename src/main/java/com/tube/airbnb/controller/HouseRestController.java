package com.tube.airbnb.controller;

import com.tube.airbnb.config.BaseResponse;
import com.tube.airbnb.config.BaseResponseStatus;
import com.tube.airbnb.dto.GetHouseInfoRes;
import com.tube.airbnb.dto.PatchHouseReq;
import com.tube.airbnb.dto.PostHouseReq;
import com.tube.airbnb.dto.PostHouseRes;
import com.tube.airbnb.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import static com.tube.airbnb.config.BaseResponseStatus.UPDATE_SUCCESS;

@RestController
@RequestMapping("/houses")
public class HouseRestController {
    HouseService houseService;

    @Autowired
    public HouseRestController(HouseService houseService) {
        this.houseService = houseService;
    }

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
