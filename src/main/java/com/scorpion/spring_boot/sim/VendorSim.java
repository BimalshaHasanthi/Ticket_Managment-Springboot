package com.scorpion.spring_boot.sim;

import com.scorpion.spring_boot.dto.TicketDTO;
import com.scorpion.spring_boot.entity.Vendor;
import com.scorpion.spring_boot.log.LogFile;
import com.scorpion.spring_boot.service.TicketService;
import com.scorpion.spring_boot.service.TicketServiceImpl;

public class VendorSim extends Thread {
    private final TicketService ticketService = new TicketServiceImpl();
    private final Vendor vendor;
    private final TicketBlockingQueue ticketQueue;
    private final int ticketReleaseRate;

    private final ThreadManager threadManager;

    public VendorSim(Vendor vendor, TicketBlockingQueue ticketQueue, int ticketReleaseRate, ThreadManager threadManager) {
        this.vendor = vendor;
        this.ticketQueue = ticketQueue;
        this.ticketReleaseRate = ticketReleaseRate;
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
                while (!ticketQueue.MAX_RELEASED) {
                    Thread.sleep(ticketReleaseRate);

                    TicketDTO ticket = ticketService.releaseTicket(vendor.getVendorId());

                    if (ticket != null) {
                        ticketQueue.releaseTicket(ticket);

                        LogFile logFile = new LogFile();
                        logFile.info(vendor.getVendorId() + " ---> " + ticket.getTicketId());
                    }
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
