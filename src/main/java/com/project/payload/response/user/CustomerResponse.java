package com.project.payload.response.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.entity.concretes.business.BalanceHistory;
import com.project.entity.concretes.business.BonusHistory;
import com.project.entity.concretes.business.PurchasedProductHistory;
import com.project.entity.concretes.user.Address;
import com.project.payload.response.abstracts.BaseUserResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerResponse extends BaseUserResponse {

    private List<BalanceHistory> balanceHistories;
    private Address address;
    private List<BonusHistory> bonusHistories;
    private List<PurchasedProductHistory> purchasedProductHistories;
    private boolean isActive;


}
