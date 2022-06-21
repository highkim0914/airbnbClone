package com.tube.airbnb.service;

import com.tube.airbnb.config.BaseResponseStatus;
import com.tube.airbnb.dto.GetReservationRes;
import com.tube.airbnb.dto.PostReservationReq;
import com.tube.airbnb.dto.PostReservationRes;
import com.tube.airbnb.entity.House;
import com.tube.airbnb.entity.Reservation;
import com.tube.airbnb.entity.User;
import com.tube.airbnb.exception.BaseException;
import com.tube.airbnb.repository.HouseRepository;
import com.tube.airbnb.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private ReservationRepository reservationRepository;

    private HouseRepository houseRepository;

    private UserService userService;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, HouseRepository houseRepository, UserService userService) {
        this.reservationRepository = reservationRepository;
        this.houseRepository = houseRepository;
        this.userService = userService;
    }


    public PostReservationRes createReservation(PostReservationReq postReservationReq) throws BaseException {
        User user = userService.findByUserId(postReservationReq.getUserId());
        House house = houseRepository.findById(postReservationReq.getHouseId())
                .orElseThrow(()->new BaseException(BaseResponseStatus.HOUSES_WRONG_HOUSE_ID));

        Reservation reservation = postReservationReq.toEntity(user, house);
        reservationRepository.save(reservation);
        return PostReservationRes.from(reservation);
    }

    public void cancelReservation(long id) throws BaseException {
        Reservation reservation = getReservation(id);

        userService.checkJwtByUserId(reservation.getUser().getId());

        reservation.setStatus(1);

        reservationRepository.save(reservation);
    }

    public void approveReservation(long id, long hostId) throws BaseException {
        Reservation reservation = getReservation(id);

        if(hostId != reservation.getHouse().getHost().getId()){
            throw new BaseException(BaseResponseStatus.APPROVE_RESERVATION_HOSTS_NOT_VALID);
        }
        reservation.setIsApproved(true);
        reservationRepository.save(reservation);
    }

    public List<GetReservationRes> getReservationsOfUser(long userId) throws BaseException{
        return getReservationsByUserId(userId).stream()
                .map(GetReservationRes::from)
                .collect(Collectors.toList());
    }

    public Reservation getReservation(long id) throws BaseException{
        return reservationRepository.findById(id)
                .orElseThrow(()->new BaseException(BaseResponseStatus.RESERVATION_WRONG_RESERVATION_ID));
    }

    public List<Reservation> getReservationsByUserId(long userId){
        return reservationRepository.findAllByUserId(userId);
    }
}
