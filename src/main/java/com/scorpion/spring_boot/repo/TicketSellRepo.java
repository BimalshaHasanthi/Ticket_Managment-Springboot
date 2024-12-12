package com.scorpion.spring_boot.repo;

import com.scorpion.spring_boot.entity.TicketSell;
import com.scorpion.spring_boot.entity.TicketSellKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketSellRepo extends JpaRepository<TicketSell, TicketSellKey> {
}
