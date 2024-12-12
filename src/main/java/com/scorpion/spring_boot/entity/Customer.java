package com.scorpion.spring_boot.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @Column(name = "customer_id", nullable = false, unique = true, length = 8)
    private String customerId;
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Column(name = "phone_number", nullable = false, length = 15)
    private String phoneNumber;
    @Column(name = "customer_type", nullable = false, length = 10)
    private String customerType;

    public Customer() {}

    public Customer(String customerId, String name, String email, String phoneNumber, String customerType) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.customerType = customerType;
    }

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
