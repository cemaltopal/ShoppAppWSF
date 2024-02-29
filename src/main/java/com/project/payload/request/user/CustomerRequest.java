package com.project.payload.request.user;

import com.project.entity.concretes.user.Address;
import com.project.entity.concretes.user.BankCard;
import com.project.payload.request.abstracts.BaseUserRequest;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class CustomerRequest extends BaseUserRequest {

    @NotNull(message = "Please enter your address information!")
    private Address address;

    @NotNull(message = "Please enter your BankCard information!")
    private BankCard bankCard;
}
