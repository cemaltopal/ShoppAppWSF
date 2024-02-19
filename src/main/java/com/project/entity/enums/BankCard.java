package com.project.entity.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class BankCard {
    private Long id;
    private String name;
    private String cardNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate expirationDate;
    private String securityCode;
}
