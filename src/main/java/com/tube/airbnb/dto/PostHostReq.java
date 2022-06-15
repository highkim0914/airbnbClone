package com.tube.airbnb.dto;

import com.tube.airbnb.entity.Host;
import com.tube.airbnb.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
public class PostHostReq {
    private long userId;
    private String name;
    private String email;
    private String detail;

    public Host toEntity(){
        return Host.builder()
                .detail(detail)
                .email(email)
                .name(name)
                .isVerified(false)
                .build();
    }
}
