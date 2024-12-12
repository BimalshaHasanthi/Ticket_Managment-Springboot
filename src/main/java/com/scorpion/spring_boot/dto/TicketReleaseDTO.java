package com.scorpion.spring_boot.dto;

import lombok.*;

@NoArgsConstructor
public class TicketReleaseDTO {
    private String ticketId;
    private String vendorId;

    public TicketReleaseDTO(String ticketId, String vendorId) {
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
