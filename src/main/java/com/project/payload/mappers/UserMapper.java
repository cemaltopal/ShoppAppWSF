package com.project.payload.mappers;

import com.project.entity.concretes.user.User;
import com.project.payload.request.abstracts.BaseUserRequest;
import com.project.payload.request.user.CustomerRequest;
import com.project.payload.request.user.UserRequest;
import com.project.payload.response.user.CustomerResponse;
import com.project.payload.response.user.GuestCustomerResponse;
import com.project.payload.response.user.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse mapUserToUserResponse(User user){
        return  UserResponse.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .surname(user.getSurname())
                .phoneNumber(user.getPhoneNumber())
                .gender(user.getGender())
                .birthDay(user.getBirthDay())
                .birthPlace(user.getBirthPlace())
                .ssn(user.getSsn())
                .email(user.getEmail())
                .userRole(user.getUserRole().getRoleType().name())
                .build();
    }

    public User mapUserRequestToUser(BaseUserRequest userRequest){

        return User.builder()
                .username(userRequest.getUsername())
                .name(userRequest.getName())
                .surname(userRequest.getSurname())
                .password(userRequest.getPassword())
                .ssn(userRequest.getSsn())
                .birthDay(userRequest.getBirthDay())
                .birthPlace(userRequest.getBirthPlace())
                .phoneNumber(userRequest.getPhoneNumber())
                .gender(userRequest.getGender())
                .email(userRequest.getEmail())
                .built_in(userRequest.getBuiltIn())
                .build();
    }

    public CustomerResponse mapUserToCustomerResponse(User customer){

        return CustomerResponse.builder()
                .userId(customer.getId())
                .username(customer.getUsername())
                .name(customer.getName())
                .surname(customer.getSurname())
                .birthDay(customer.getBirthDay())
                .birthPlace(customer.getBirthPlace())
                .phoneNumber(customer.getPhoneNumber())
                .gender(customer.getGender())
                .email(customer.getEmail())
                .isActive(customer.isActive())
                .build();
    }
    public GuestCustomerResponse mapUserToGuestCustomerResponse(User guestCustomer){

        return GuestCustomerResponse.builder()
                .userId(guestCustomer.getId())
                .username(guestCustomer.getUsername())
                .name(guestCustomer.getName())
                .surname(guestCustomer.getSurname())
                .birthDay(guestCustomer.getBirthDay())
                .birthPlace(guestCustomer.getBirthPlace())
                .ssn(guestCustomer.getSsn())
                .phoneNumber(guestCustomer.getPhoneNumber())
                .gender(guestCustomer.getGender())
                .email(guestCustomer.getEmail())
                .build();
    }


    public User mapUserRequestToUpdatedUser(UserRequest userRequest, Long userId) {
        return User.builder()
                .id(userId)
                .username(userRequest.getUsername())
                .name(userRequest.getName())
                .surname(userRequest.getSurname())
                .password(userRequest.getPassword())
                .ssn(userRequest.getSsn())
                .birthDay(userRequest.getBirthDay())
                .birthPlace(userRequest.getBirthPlace())
                .phoneNumber(userRequest.getPhoneNumber())
                .gender(userRequest.getGender())
                .email(userRequest.getEmail())
                .build();
    }

    public User mapCustomerRequestToUser(CustomerRequest customerRequest) {
        return User.builder()
                .name(customerRequest.getName())
                .surname(customerRequest.getSurname())
                .ssn(customerRequest.getSsn())
                .username(customerRequest.getUsername())
                .birthDay(customerRequest.getBirthDay())
                .birthPlace(customerRequest.getBirthPlace())
                .password(customerRequest.getPassword())
                .phoneNumber(customerRequest.getPhoneNumber())
                .email(customerRequest.getEmail())
                .built_in(customerRequest.getBuiltIn())
                .gender(customerRequest.getGender())
                .address(customerRequest.getAddress())
                .bankCard(customerRequest.getBankCard())
                .build();
    }
    public User mapCustomerRequestToUpdatedUser(CustomerRequest customerRequest, Long userId) {
        return User.builder()
                .id(userId)
                .name(customerRequest.getName())
                .surname(customerRequest.getSurname())
                .ssn(customerRequest.getSsn())
                .username(customerRequest.getUsername())
                .birthDay(customerRequest.getBirthDay())
                .birthPlace(customerRequest.getBirthPlace())
                .password(customerRequest.getPassword())
                .phoneNumber(customerRequest.getPhoneNumber())
                .email(customerRequest.getEmail())
                .built_in(customerRequest.getBuiltIn())
                .gender(customerRequest.getGender())
                .address(customerRequest.getAddress())
                .bankCard(customerRequest.getBankCard())
                .build();
    }
}