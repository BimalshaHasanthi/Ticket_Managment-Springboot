package com.scorpion.spring_boot.sim;

import com.scorpion.spring_boot.dto.TicketDTO;
import com.scorpion.spring_boot.log.LogFile;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TicketBlockingQueue {
    public final BlockingQueue<TicketDTO> ticketQueue;
    private final int totalTickets;
    private final int maxTicketCapacity;
    private int ticketCount = 0;
    public boolean MAX_RELEASED = false;

    public TicketBlockingQueue(int totalTickets, int maxTicketCapacity) {
        this.ticketQueue = new ArrayBlockingQueue<>(maxTicketCapacity);
        this.totalTickets = totalTickets;
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public synchronized void releaseTicket(TicketDTO ticket) throws InterruptedException {
        if (ticketCount == totalTickets) {
            LogFile logFile = new LogFile();
            logFile.info("All tickets have been released.");
            MAX_RELEASED = true;
            return;
        }

        while (ticketQueue.size() == maxTicketCapacity) {
            LogFile logFile = new LogFile();
            logFile.warn("Queue is full. Waiting for tickets to be retrieved.");
            wait();
        }

        ticketQueue.put(ticket);
        ticketCount++;

        LogFile logFile = new LogFile();
        logFile.info("Ticket " + ticket.getTicketId() + " released to the queue.");

        notify();
    }

    public synchronized TicketDTO retrieveTicket() throws InterruptedException {
        if (ticketCount == totalTickets && ticketQueue.isEmpty()) {
            LogFile logFile = new LogFile();
            logFile.info("All tickets have been retrieved.");
            return null;
        }

        while (ticketQueue.isEmpty()) {
            LogFile logFile = new LogFile();
            logFile.warn("Queue is empty. Waiting for tickets to be released.");
            wait();
        }

        TicketDTO ticket = ticketQueue.take();

        LogFile logFile = new LogFile();
        logFile.info("Ticket " + ticket.getTicketId() + " retrieved from the queue.");

        notify();
        return ticket;
    }
}
