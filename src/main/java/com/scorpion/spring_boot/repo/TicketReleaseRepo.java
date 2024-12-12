package com.scorpion.spring_boot.repo;

import com.scorpion.spring_boot.entity.TicketRelease;
import com.scorpion.spring_boot.entity.TicketReleaseKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketReleaseRepo extends JpaRepository<TicketRelease, TicketReleaseKey> {
}
