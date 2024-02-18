package com.project.entity.enums;

public enum RoleType {
    ADMIN("Admin"),
    CUSTOMER("Customer"),
    GUEST("Guest"),
    ACCOUNTANT("Accountant");

    public String name;

    RoleType(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }
}
