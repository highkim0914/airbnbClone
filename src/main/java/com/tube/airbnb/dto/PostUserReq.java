package com.tube.airbnb.dto;

import com.tube.airbnb.config.Gender;
import com.tube.airbnb.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class PostUserReq {
    private String name;
    private String email;
    private String password;
    private Gender gender;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String address;
    private String emergencyContact;

    public User toEntity(){
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .gender(gender)
                .dateOfBirth(dateOfBirth)
                .phoneNumber(phoneNumber)
                .address(address)
                .emergencyContact(emergencyContact)
                .build();
    }
}
