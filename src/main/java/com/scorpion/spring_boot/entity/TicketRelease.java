package com.scorpion.spring_boot.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Entity
public class TicketRelease {
    @EmbeddedId
    private TicketReleaseKey ticketReleaseKey;

    public TicketRelease(TicketReleaseKey ticketReleaseKey) {
        this.ticketReleaseKey = ticketReleaseKey;
    }

    public TicketReleaseKey getTicketReleaseKey() {
        return ticketReleaseKey;
    }
}

