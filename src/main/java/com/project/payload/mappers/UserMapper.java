package com.project.payload.mappers;

import com.project.entity.concretes.user.User;
import com.project.payload.response.user.UserResponse;

public class UserMapper {

    public UserResponse mapUserToUserResponse(User user) {
        return UserResponse.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .surname(user.getSurname())
                .phoneNumber(user.getPhoneNumber())
                .gender(user.getGender())
                .email(user.getEmail())
                .birthDay(user.getBirthDay())
                .birthPlace(user.getBirthPlace())
                .ssn(user.getSsn())
                .userRole(user.getUserRole().getRoleType().name)
                .build();
    }
}
