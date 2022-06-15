package com.tube.airbnb.repository;

import com.tube.airbnb.entity.House;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HouseRepository extends JpaRepository<House,Long> {
    List<House> findAllById(long id);
    Page<House> findAll(Pageable pageable);

//    @Query("select distinct h from House h " +
//            "join fetch h.houseFacilities f " +
//            "join fetch f.facility " +
//            "where h.id = :id ")
    @EntityGraph(attributePaths = {"houseFacilities","houseFacilities.facility"})
    House findWithHouseFacilitiesAndFacilityById(@Param(value = "id") long id);
}
