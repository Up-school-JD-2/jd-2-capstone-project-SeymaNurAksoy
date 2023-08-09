package io.upschool.repository;

import io.upschool.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository  extends JpaRepository<Ticket,Long> {

    @Query(value = "SELECT o FROM Ticket o WHERE o.active = true")
    List<Ticket> getActiveTickets();

    @Query(value = "SELECT o FROM Ticket o WHERE o.active = false")
    List<Ticket> getCancelTickets();

}
