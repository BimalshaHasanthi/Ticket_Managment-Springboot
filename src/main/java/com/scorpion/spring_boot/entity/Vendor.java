package com.scorpion.spring_boot.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Entity
public class Vendor {
    @Id
    @Column(name = "vendor_id", nullable = false, unique = true, length = 8)
    private String vendorId;
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Column(name = "phone_number", nullable = false, length = 15)
    private String phoneNumber;

    public Vendor() {
    }

    public Vendor(String vendorId, String name, String email, String phoneNumber) {
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
