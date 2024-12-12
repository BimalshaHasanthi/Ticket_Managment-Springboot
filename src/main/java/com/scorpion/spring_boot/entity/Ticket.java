package com.scorpion.spring_boot.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Entity
public class Ticket {
    @Id
    @Column(name = "ticket_id", nullable = false, unique = true, length = 8)
    private String ticketId;
    @Column(name = "ticket_status", nullable = false, length = 10)
    private String ticketStatus;
    @Column(name = "price", nullable = false)
    private double price;

//    public Ticket() {
//    }

    public Ticket(String ticketId, String ticketStatus, double price) {
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

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public double getPrice() {
        return price;
    }
}
