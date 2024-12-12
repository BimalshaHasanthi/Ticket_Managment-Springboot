package com.scorpion.spring_boot.controller;

import com.scorpion.spring_boot.dto.SystemConfigDTO;
import com.scorpion.spring_boot.dto.TicketDTO;
import com.scorpion.spring_boot.dto.TicketRetrieveDTO;
import com.scorpion.spring_boot.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping("/save-config")
    public ResponseEntity<String> saveConfig(@RequestBody SystemConfigDTO systemConfig) {
        boolean isConfigSaved = ticketService.saveConfig(systemConfig);
        if (isConfigSaved) {
            return new ResponseEntity<>("Config saved successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Config not saved", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-config")
    public ResponseEntity<SystemConfigDTO> getConfig() {
        SystemConfigDTO systemConfig = ticketService.getConfig();
        if (systemConfig != null) {
            return new ResponseEntity<>(systemConfig, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("release-ticket/{vendorId}")
    public ResponseEntity<String> releaseTicket(@PathVariable String vendorId) {
        TicketDTO releasedTicket = ticketService.releaseTicket(vendorId);
        if (releasedTicket != null) {
            return new ResponseEntity<>("Ticket released successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Ticket not released", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("retrieve-ticket")
    public ResponseEntity<String> retrieveTicket(@RequestBody TicketRetrieveDTO ticket) {
        boolean isTicketRetrieved = ticketService.retrieveTicket(ticket);
        if (isTicketRetrieved) {
            return new ResponseEntity<>("Ticket retrieved successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Ticket not retrieved", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("start-simulation")
    public ResponseEntity<String> startSimulation() {
        boolean isSimulationStarted = ticketService.startSimulation();
        if (isSimulationStarted) {
            return new ResponseEntity<>("Simulation started successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Simulation not started", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("stop-simulation")
    public ResponseEntity<String> stopSimulation() {
        boolean isSimulationStopped = ticketService.stopSimulation();
        if (isSimulationStopped) {
            return new ResponseEntity<>("Simulation stopped successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Simulation not stopped", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("reset-simulation")
    public ResponseEntity<String> resetSimulation() {
        boolean isSimulationReset = ticketService.resetSimulation();
        if (isSimulationReset) {
            return new ResponseEntity<>("Simulation reset successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Simulation not reset", HttpStatus.BAD_REQUEST);
        }
    }
}
