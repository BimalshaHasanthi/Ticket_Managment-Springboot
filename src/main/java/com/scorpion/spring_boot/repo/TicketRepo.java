package com.scorpion.spring_boot.repo;

import com.scorpion.spring_boot.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TicketRepo extends JpaRepository<Ticket, String> {
    @Query(value = "SELECT ticket_id FROM ticket ORDER BY ticket_id DESC LIMIT 1", nativeQuery = true)
    String getLatestId();
}
