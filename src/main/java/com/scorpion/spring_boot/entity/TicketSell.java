package com.scorpion.spring_boot.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Entity
public class TicketSell {
    @EmbeddedId
    private TicketSellKey ticketSellKey;

    public TicketSell(TicketSellKey ticketSellKey) {
        this.ticketSellKey = ticketSellKey;
    }

    public TicketSellKey getTicketSellKey() {
        return ticketSellKey;
    }
}
