package com.scorpion.spring_boot.dto;

import lombok.*;

@NoArgsConstructor
public class TicketRetrieveDTO {
    private String ticketId;
    private String customerId;

    public TicketRetrieveDTO(String ticketId, String customerId) {
        this.ticketId = ticketId;
        this.customerId = customerId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getCustomerId() {
        return customerId;
    }
}
