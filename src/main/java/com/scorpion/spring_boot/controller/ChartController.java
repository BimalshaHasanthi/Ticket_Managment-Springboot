package com.scorpion.spring_boot.controller;

import com.scorpion.spring_boot.dto.TicketReleaseDTO;
import com.scorpion.spring_boot.dto.TicketRetrieveDTO;
import com.scorpion.spring_boot.service.ChartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/chart")
public class ChartController {
    @Autowired
    private ChartService chartService;

    @GetMapping("/get-all-released")
    public ResponseEntity<List<TicketReleaseDTO>> getAllReleasedTickets() {
        List<TicketReleaseDTO> tickets = chartService.getAllReleasedTickets();
        if (tickets != null) {
            return new ResponseEntity<>(tickets, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-all-retrieved")
    public ResponseEntity<List<TicketRetrieveDTO>> getAllRetrievedTickets() {
        List<TicketRetrieveDTO> tickets = chartService.getAllRetrievedTickets();
        if (tickets != null) {
            return new ResponseEntity<>(tickets, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
