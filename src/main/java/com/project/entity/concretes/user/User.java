package com.project.entity.concretes.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.entity.concretes.business.BalanceHistory;
import com.project.entity.concretes.business.BonusHistory;
import com.project.entity.concretes.business.PurchasedProductHistory;
import com.project.entity.enums.Gender;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "t_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String ssn;

    private String name;
    private String surname;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDay;

    private String birthPlace;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(unique = true)
    private String phoneNumber;

    @Column(unique = true)
    private String email;

    private Boolean built_in;

    private boolean isActive;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Embedded
    private BankCard bankCard;
    @Embedded
    private Address address;

    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    private List<BalanceHistory> balanceHistories;

    @JsonIgnore
    @OneToMany(mappedBy = "customer",cascade = CascadeType.REMOVE)
    private List<BonusHistory> bonusHistories;

    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    private List<PurchasedProductHistory> purchasedProductHistories;

    @OneToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UserRole userRole;


}
