package com.scorpion.spring_boot.dto;

import lombok.*;

@NoArgsConstructor
public class CustomerDTO {
    private String customerId;
    private String name;
    private String email;
    private String phoneNumber;

    public CustomerDTO(String customerId, String name, String email, String phoneNumber, String customerType) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.customerType = customerType;
    }

    private String customerType;

    public String getCustomerType() {
        return customerType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getCustomerId() {
        return customerId;
    }
}
