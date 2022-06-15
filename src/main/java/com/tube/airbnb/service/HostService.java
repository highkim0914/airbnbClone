package com.tube.airbnb.service;

import com.tube.airbnb.dto.*;
import com.tube.airbnb.entity.House;
import com.tube.airbnb.entity.Reservation;
import com.tube.airbnb.exception.BaseException;
import com.tube.airbnb.config.BaseResponseStatus;
import com.tube.airbnb.entity.Host;
import com.tube.airbnb.entity.User;
import com.tube.airbnb.repository.HostRepository;
import com.tube.airbnb.repository.ReservationRepository;
import com.tube.airbnb.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Stack;
import java.util.Vector;
import java.util.stream.Collectors;

import static com.tube.airbnb.config.BaseResponseStatus.INVALID_JWT;

@Service
public class HostService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    StringBuilder sb;
    StringBuffer sb2;

    @Autowired
    HostRepository hostRepository;

    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;

    @Autowired
    ReservationRepository reservationRepository;

    public Host getHost(long hostId) throws BaseException {
        return hostRepository.findById(hostId)
                .orElseThrow(()->new BaseException(BaseResponseStatus.HOSTS_EMPTY_HOST_ID));
    }

    public PostHostRes createHost(PostHostReq postHostReq) throws BaseException {
        long userId = postHostReq.getUserId();
        userService.checkJwtByUserId(userId);
        userService.checkAlreadyHost(userId);
        Host host = postHostReq.toEntity();

        User user = userService.findByUserId(userId);
        hostRepository.save(host);
        userService.setHost(user, host);

        return PostHostRes.from(host);
    }

    public Host patchHost(long hostId, PatchHostReq patchHostReq)throws BaseException{
        validateHostJwtTokenByHostId(hostId);

        Host host = getHost(hostId);
        host.setDetail(patchHostReq.getDetail());
        host.setEmail(patchHostReq.getEmail());
        hostRepository.save(host);
        return host;
    }


    public void validateHostJwtTokenByHostId(long id) throws BaseException{
        long userId = getUserIdOfHost(id);
        if(userId != jwtService.getUserIdx()){
            throw new BaseException(INVALID_JWT);
        }
    }

    public long getUserIdOfHost(long hostId) throws BaseException{
        User user = userService.findByHostId(hostId);
        return user.getId();
    }

    public void deleteById(long id) throws BaseException{
        validateHostJwtTokenByHostId(id);

        Host host = getHost(id);
        host.setStatus(1);
        userService.setHost(userService.findByHostId(id), null);
        hostRepository.save(host);
    }

    public GetHostRes getHostRes(long id) throws BaseException{
        Host host = getHost(id);
        List<House> houses = host.getHouses();
        return GetHostRes.from(host,houses);
    }

    public List<GetReservationRes> getReservationResList(long houseId) {
        List<Reservation> reservations = reservationRepository.findAllByHouseId(houseId);
        return reservations.stream()
                .map(GetReservationRes::from)
                .collect(Collectors.toList());
    }
}
