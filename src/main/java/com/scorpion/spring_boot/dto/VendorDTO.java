package com.scorpion.spring_boot.dto;

import lombok.*;

@NoArgsConstructor
public class VendorDTO {
    private String vendorId;
    private String name;
    private String email;
    private String phoneNumber;

    public VendorDTO(String vendorId, String name, String email, String phoneNumber) {
        this.vendorId = vendorId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getVendorId() {
        return vendorId;
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
