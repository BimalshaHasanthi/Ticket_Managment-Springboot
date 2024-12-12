package com.scorpion.spring_boot.service;

import com.scorpion.spring_boot.dto.TicketReleaseDTO;
import com.scorpion.spring_boot.dto.TicketRetrieveDTO;

import java.util.List;

public interface ChartService {
    List<TicketReleaseDTO> getAllReleasedTickets();
    List<TicketRetrieveDTO> getAllRetrievedTickets();
}
