package com.scorpion.spring_boot.service;

import com.scorpion.spring_boot.dto.SystemConfigDTO;
import com.scorpion.spring_boot.dto.TicketDTO;
import com.scorpion.spring_boot.dto.TicketRetrieveDTO;
import com.scorpion.spring_boot.entity.*;
import com.scorpion.spring_boot.log.LogFile;
import com.scorpion.spring_boot.repo.*;
import com.scorpion.spring_boot.sim.CustomerSim;
import com.scorpion.spring_boot.sim.ThreadManager;
import com.scorpion.spring_boot.sim.TicketBlockingQueue;
import com.scorpion.spring_boot.sim.VendorSim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {
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

    private final ThreadManager threadManager = new ThreadManager();
    private boolean isStarted = false;

    private final String CONFIG_DIR_PATH = "src/main/java/com/scorpion/spring_boot/config";
    private final String CONFIG_FILE_NAME = "system_config.txt";

    @Override
    public boolean saveConfig(SystemConfigDTO systemConfig) {
        // Define the directory and file path
        Path configDir = Paths.get(CONFIG_DIR_PATH);
        Path filePath = configDir.resolve(CONFIG_FILE_NAME);

        try {
            // Create the directory if it doesn't exist
            if (!Files.exists(configDir)) {
                Files.createDirectories(configDir);
            }

            // Prepare the content to write with each item on a new line
            String content = "Total Tickets: " + systemConfig.getTotalTickets() + "\n" +
                    "Ticket Release Rate: " + systemConfig.getTicketReleaseRate() + "\n" +
                    "Ticket Retrieval Rate: " + systemConfig.getTicketRetrievalRate() + "\n" +
                    "Max Ticket Capacity: " + systemConfig.getMaxTicketCapacity() + "\n";

            // Write content to the file, creating or overwriting the file
            Files.writeString(filePath, content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Configuration saved to " + filePath.toString());

            LogFile logFile = new LogFile();
            logFile.info("Configuration saved to " + CONFIG_FILE_NAME);
            logFile.info("Total Tickets: " + systemConfig.getTotalTickets());
            logFile.info("Ticket Release Rate: " + systemConfig.getTicketReleaseRate());
            logFile.info("Ticket Retrieval Rate: " + systemConfig.getTicketRetrievalRate());
            logFile.info("Max Ticket Capacity: " + systemConfig.getMaxTicketCapacity());

            return true; // Indicate success

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public SystemConfigDTO getConfig() {
        // Define the path to the config file
        Path configDir = Paths.get(CONFIG_DIR_PATH);
        Path filePath = configDir.resolve(CONFIG_FILE_NAME);

        try {
            // Read all lines from the configuration file
            List<String> lines = Files.readAllLines(filePath);

            if (lines.size() < 4) {
                // In case there are missing parameters
                System.err.println("Configuration file is missing data");
                return null;
            }

            // Extract parameters from the lines
            int totalTickets = Integer.parseInt(lines.get(0).split(":")[1].trim());
            int ticketReleaseRate = Integer.parseInt(lines.get(1).split(":")[1].trim());
            int ticketRetrievalRate = Integer.parseInt(lines.get(2).split(":")[1].trim());
            int maxTicketCapacity = Integer.parseInt(lines.get(3).split(":")[1].trim());

            LogFile logFile = new LogFile();
            logFile.info("Configuration loaded from " + CONFIG_FILE_NAME);
            logFile.info("Total Tickets: " + totalTickets);
            logFile.info("Ticket Release Rate: " + ticketReleaseRate);
            logFile.info("Ticket Retrieval Rate: " + ticketRetrievalRate);
            logFile.info("Max Ticket Capacity: " + maxTicketCapacity);

            // Create a new SystemConfigDTO object with the extracted values
            return new SystemConfigDTO(totalTickets, ticketReleaseRate, ticketRetrievalRate, maxTicketCapacity);

        } catch (IOException e) {
            e.printStackTrace();
            return null; // Indicate failure to read the config
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.err.println("Error parsing configuration values.");
            return null;
        }
    }

    @Override
    public TicketDTO releaseTicket(String vendorId) {
        Ticket ticket = new Ticket(this.getNextTicketId(), TicketStatus.AVAILABLE.toString(), 1000.00);
        ticketRepo.save(ticket);

        TicketRelease ticketRelease = new TicketRelease(new TicketReleaseKey(ticket.getTicketId(), vendorId));
        ticketReleaseRepo.save(ticketRelease);

        return new TicketDTO(ticket.getTicketId(), ticket.getTicketStatus(), ticket.getPrice());
    }

    @Override
    public boolean retrieveTicket(TicketRetrieveDTO ticketRetrieve) {
        Ticket ticket = ticketRepo.findById(ticketRetrieve.getTicketId()).orElse(null);

        if (ticket != null) {
            ticket.setTicketStatus(TicketStatus.SOLD_OUT.toString());
            ticketRepo.save(ticket);

            TicketSell ticketSell = new TicketSell(new TicketSellKey(ticketRetrieve.getTicketId(), ticketRetrieve.getCustomerId()));
            ticketSellRepo.save(ticketSell);
            return true;
        }

        return false;
    }

    @Override
    public boolean startSimulation() {
        if (!isStarted) {
            isStarted = true;
            SystemConfigDTO systemConfig = getConfig();
            TicketBlockingQueue ticketQueue = new TicketBlockingQueue(systemConfig.getTotalTickets(), systemConfig.getMaxTicketCapacity());
            addVendorThreads(ticketQueue);
            addCustomerThreads(ticketQueue);

            LogFile logFile = new LogFile();
            logFile.info("Simulation started");
        } else {
            threadManager.resume();

            LogFile logFile = new LogFile();
            logFile.info("Simulation resumed");
        }
        return true;
    }

    @Override
    public boolean stopSimulation() {
        threadManager.pause();

        LogFile logFile = new LogFile();
        logFile.info("Simulation paused");

        return true;
    }

    @Override
    public boolean resetSimulation() {
        threadManager.pause();
        isStarted = false;

        LogFile logFile = new LogFile();
        logFile.info("Simulation reset");

        return true;
    }

    private String getNextTicketId() {
        String lastTicketId = ticketRepo.getLatestId();

        if (lastTicketId != null) {
            int index = Integer.parseInt(lastTicketId.split("-")[1]);
            if (index < 9) {
                return "TIC-000" + ++index;
            } else if (index < 99) {
                return "TIC-00" + ++index;
            } else if (index < 999) {
                return "TIC-0" + ++index;
            } else {
                return "TIC-" + ++index;
            }
        } else {
            return "TIC-0001";
        }
    }

    private void addVendorThreads(TicketBlockingQueue ticketQueue) {
        List<Vendor> vendors = vendorRepo.findAll();
        for (Vendor vendor : vendors) {
            VendorSim vendorSim = new VendorSim(vendor, ticketQueue, 1000, threadManager);
            vendorSim.start();
        }
    }

    private void addCustomerThreads(TicketBlockingQueue ticketQueue) {
        List<Customer> customers = customerRepo.findAll();
        for (Customer customer : customers) {
            CustomerSim customerSim = new CustomerSim(customer, ticketQueue, 1000, threadManager);
            customerSim.start();
        }
    }
}
