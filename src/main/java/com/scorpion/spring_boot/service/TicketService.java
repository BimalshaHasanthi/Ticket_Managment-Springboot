package com.scorpion.spring_boot.service;

import com.scorpion.spring_boot.dto.SystemConfigDTO;
import com.scorpion.spring_boot.dto.TicketDTO;
import com.scorpion.spring_boot.dto.TicketRetrieveDTO;

public interface TicketService {
    boolean saveConfig(SystemConfigDTO systemConfig);
    SystemConfigDTO getConfig();
    TicketDTO releaseTicket(String vendorId);
    boolean retrieveTicket(TicketRetrieveDTO ticket);
    boolean startSimulation();
    boolean stopSimulation();
    boolean resetSimulation();
}
