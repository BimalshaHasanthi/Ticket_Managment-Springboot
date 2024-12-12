package com.scorpion.spring_boot.service;

import com.scorpion.spring_boot.dto.TicketReleaseDTO;
import com.scorpion.spring_boot.dto.TicketRetrieveDTO;
import com.scorpion.spring_boot.entity.TicketRelease;
import com.scorpion.spring_boot.entity.TicketReleaseKey;
import com.scorpion.spring_boot.entity.TicketSell;
import com.scorpion.spring_boot.entity.TicketSellKey;
import com.scorpion.spring_boot.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ChartServiceImpl implements ChartService {
    @Autowired
    private TicketRepo ticketRepo;
    @Autowired
    private TicketReleaseRepo ticketReleaseRepo;
    @Autowired
    private TicketSellRepo ticketSellRepo;
    @Autowired
    private VendorRepo vendorRepo;
    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public List<TicketReleaseDTO> getAllReleasedTickets() {
        List<TicketRelease> ticketReleaseList = ticketReleaseRepo.findAll();
        List<TicketReleaseDTO> ticketReleaseDTOs = new ArrayList<>();

        for (TicketRelease ticketRelease : ticketReleaseList) {
            TicketReleaseKey key = ticketRelease.getTicketReleaseKey();
            ticketReleaseDTOs.add(new TicketReleaseDTO(key.getTicketId(), key.getVendorId()));
        }

        return ticketReleaseDTOs;
    }

    @Override
    public List<TicketRetrieveDTO> getAllRetrievedTickets() {
        List<TicketSell> ticketSellList = ticketSellRepo.findAll();
        List<TicketRetrieveDTO> ticketRetrieveDTOs = new ArrayList<>();

        for (TicketSell ticketSell : ticketSellList) {
            TicketSellKey key = ticketSell.getTicketSellKey();
            ticketRetrieveDTOs.add(new TicketRetrieveDTO(key.getTicketId(), key.getCustomerId()));
        }

        return ticketRetrieveDTOs;
    }
}
