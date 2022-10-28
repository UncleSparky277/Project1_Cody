package dev.cruz.services;

import dev.cruz.entities.Ticket;

import java.util.List;

public interface TicketService {
    //create
    Ticket createTicket(Ticket ticket, int id);

    //read
    List<Ticket> getTicketById(int id);
    Ticket getTicketById1(int id);
    List<Ticket> getAllTickets();
    List<Ticket> getPendingTickets();

    //update
    Ticket updateTicket(Ticket ticket);

    //delete
    boolean deleteTicketById(int id);
}