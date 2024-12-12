package com.scorpion.spring_boot.dto;

import lombok.*;

@NoArgsConstructor
public class SaveVendorDTO {
    private String name;
    private String email;
    private String phoneNumber;

    public SaveVendorDTO(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
