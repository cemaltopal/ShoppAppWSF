package com.project.entity.concretes.user;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private Long id;
    private String street;
    private String city;
    private String country;
    private String zipcode;

}
