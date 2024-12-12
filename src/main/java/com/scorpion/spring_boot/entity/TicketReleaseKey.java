package com.scorpion.spring_boot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Embeddable
public class TicketReleaseKey implements Serializable {
    @Column(name = "ticket_id", nullable = false, length = 8)
    private String ticketId;
    @Column(name = "vendor_id", nullable = false, length = 8)
    private String vendorId;

    public TicketReleaseKey(String ticketId, String vendorId) {
        this.ticketId = ticketId;
        this.vendorId = vendorId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getVendorId() {
        return vendorId;
    }
}
