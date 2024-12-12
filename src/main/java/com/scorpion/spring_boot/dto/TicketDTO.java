package com.scorpion.spring_boot.dto;

import lombok.*;

@NoArgsConstructor
public class TicketDTO {
    private String ticketId;
    private String ticketStatus;
    private double price;

    public TicketDTO(String ticketId, String ticketStatus, double price) {
        this.ticketId = ticketId;
        this.ticketStatus = ticketStatus;
        this.price = price;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public double getPrice() {
        return price;
    }
}
