package com.scorpion.spring_boot.sim;

import com.scorpion.spring_boot.dto.TicketDTO;
import com.scorpion.spring_boot.dto.TicketRetrieveDTO;
import com.scorpion.spring_boot.entity.Customer;
import com.scorpion.spring_boot.log.LogFile;
import com.scorpion.spring_boot.service.TicketService;
import com.scorpion.spring_boot.service.TicketServiceImpl;

public class CustomerSim extends Thread {
    private final TicketService ticketService = new TicketServiceImpl();
    private final Customer customer;
    private final TicketBlockingQueue ticketQueue;
    private final int ticketRetrievalRate;

    private final ThreadManager threadManager;

    public CustomerSim(Customer customer, TicketBlockingQueue ticketQueue, int ticketRetrievalRate, ThreadManager threadManager) {
        this.customer = customer;
        this.ticketQueue = ticketQueue;
        this.ticketRetrievalRate = ticketRetrievalRate;
        this.threadManager = threadManager;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (threadManager.isPaused()) {
                try {
                    synchronized (threadManager) {
                        threadManager.wait();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            try {
                while (!ticketQueue.MAX_RELEASED || !ticketQueue.ticketQueue.isEmpty()) {
                    Thread.sleep(ticketRetrievalRate);

                    TicketDTO ticket = ticketQueue.retrieveTicket();

                    ticketService.retrieveTicket(new TicketRetrieveDTO(ticket.getTicketId(), customer.getCustomerId()));

                    LogFile logFile = new LogFile();
                    logFile.info(customer.getCustomerId() + " ---> " + ticket.getTicketId());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void resumeThread() {
        synchronized (threadManager) {
            threadManager.notifyAll();
        }
    }
}
