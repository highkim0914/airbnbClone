package com.tube.airbnb.repository;

import com.tube.airbnb.entity.Host;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HostRepository extends JpaRepository<Host,Long> {
    Optional<Host> findByUserId(long userId);
}
