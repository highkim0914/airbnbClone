package com.tube.airbnb.repository;

import com.tube.airbnb.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    List<Reservation> findAllByUserId(long userId);
    List<Reservation> findAllByHouseId(long houseId);
}
