package dev.cruz.repositories;

import dev.cruz.entities.Ticket;
import java.util.List;

public interface TicketDAO {

    Ticket createTicket(Ticket ticket);

    Ticket getTicketById(int id);
    List<Ticket> getAllTickets();

    Ticket updateTicket(Ticket ticket);

    boolean deleteTicketById(int id);

}
