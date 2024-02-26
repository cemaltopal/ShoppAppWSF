package com.project.entity.enums;

public enum RoleType {
    ADMIN("Admin"),
    MANAGER("Manager"),
    CUSTOMER("Customer"),
    GUEST_CUSTOMER("GuestCustomer");


    public String name;

    RoleType(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }
}
