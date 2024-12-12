package com.scorpion.spring_boot.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
public class SaveCustomerDTO {
    private String name;
    private String email;
    private String phoneNumber;
    private String customerType;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getCustomerType() {
        return customerType;
    }
}
