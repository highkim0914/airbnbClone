package com.tube.airbnb.repository;

import com.tube.airbnb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByHostId(long hostId);
}
