package com.tube.airbnb.controller;

import com.tube.airbnb.dto.*;
import com.tube.airbnb.entity.House;
import com.tube.airbnb.config.BaseResponse;
import com.tube.airbnb.config.BaseResponseStatus;
import com.tube.airbnb.entity.Host;
import com.tube.airbnb.repository.HouseRepository;
import com.tube.airbnb.service.HostService;
import com.tube.airbnb.utils.ValidationRegex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hosts")
public class HostRestController {
    @Autowired
    HostService hostService;

    @Autowired
    HouseRepository houseRepository;


    @PostMapping
    public BaseResponse<PostHostRes> createHost(@RequestBody PostHostReq postHostReq) {
        if(!ValidationRegex.isRegexEmail(postHostReq.getEmail()))
            return new BaseResponse<>(BaseResponseStatus.POST_USERS_INVALID_EMAIL);

        PostHostRes postHostRes = hostService.createHost(postHostReq);
        return new BaseResponse<>(postHostRes);

    }

    @PostMapping("/{id}")
    public BaseResponse<BaseResponseStatus> deleteHost(@PathVariable long id){
        hostService.deleteById(id);
        return new BaseResponse<>(BaseResponseStatus.DELETE_SUCCESS);
    }

    @GetMapping("/{id}")
    public BaseResponse<GetHostRes> getHost(@PathVariable long id) {
        GetHostRes getHostRes = hostService.getHostRes(id);
        return new BaseResponse<>(getHostRes);
    }

    @GetMapping("/houses/{houseId}/reservations")
    public BaseResponse<List<GetReservationRes>> getReservations(@PathVariable long houseId) {
        List<GetReservationRes> getReservationResList = hostService.getReservationResList(houseId);
        return new BaseResponse<>(getReservationResList);
    }

    @GetMapping("/houses")
    public BaseResponse<List<GetHouseRes>> getHouses(@PathVariable long houseId) {
        List<House> houses = houseRepository.findAllById(houseId);
        List<GetHouseRes> getHouseResList = houses.stream().map(GetHouseRes::from).collect(Collectors.toList());
        return new BaseResponse<>(getHouseResList);
    }

    @PatchMapping("/{hostId}")
    public BaseResponse<BaseResponseStatus> patchHost(@PathVariable long hostId, @RequestBody PatchHostReq patchHostReq){
        if(!ValidationRegex.isRegexEmail(patchHostReq.getEmail()))
            return new BaseResponse<>(BaseResponseStatus.POST_USERS_INVALID_EMAIL);

        Host host = hostService.patchHost(hostId,patchHostReq);
        return new BaseResponse<>(BaseResponseStatus.UPDATE_SUCCESS);
    }

}
