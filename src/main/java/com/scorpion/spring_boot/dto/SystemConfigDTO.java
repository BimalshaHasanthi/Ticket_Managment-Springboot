package com.scorpion.spring_boot.dto;

import lombok.*;

@NoArgsConstructor
public class SystemConfigDTO {
    private int totalTickets;
    private int ticketReleaseRate;
    private int ticketRetrievalRate;
    private int maxTicketCapacity;

    public SystemConfigDTO(int totalTickets, int ticketReleaseRate, int ticketRetrievalRate, int maxTicketCapacity) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.ticketRetrievalRate = ticketRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getTicketRetrievalRate() {
        return ticketRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }
}
