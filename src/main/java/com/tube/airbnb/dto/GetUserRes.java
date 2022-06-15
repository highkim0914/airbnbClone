package com.tube.airbnb.dto;

import com.tube.airbnb.config.Gender;
import com.tube.airbnb.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class GetUserRes {
    private long id;
    private String name;
    private String email;
    private Gender gender;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String address;
    private String emergencyContact;
    private LocalDateTime createdAt;

    public static GetUserRes from(User user){
        return GetUserRes.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .gender(user.getGender())
                .dateOfBirth(user.getDateOfBirth())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .emergencyContact(user.getEmergencyContact())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
