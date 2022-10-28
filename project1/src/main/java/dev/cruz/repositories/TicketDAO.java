package dev.cruz.repositories;

import dev.cruz.entities.Ticket;
import java.util.List;

public interface TicketDAO {

    Ticket createTicket(Ticket ticket, int id);

    List<Ticket> getTicketById(int id);
    Ticket getTicketById1(int id);
    List<Ticket> getAllTickets();
    List<Ticket> getPendingTickets();

    Ticket updateTicket(Ticket ticket);

    boolean deleteTicketById(int id);

}
