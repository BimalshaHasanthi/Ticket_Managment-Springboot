package com.scorpion.spring_boot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Embeddable
public class TicketSellKey implements Serializable {
    @Column(name = "ticket_id", nullable = false, unique = true, length = 8)
    private String ticketId;
    @Column(name = "customer_id", nullable = false, length = 8)
    private String customerId;

    public TicketSellKey(String ticketId, String customerId) {
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
