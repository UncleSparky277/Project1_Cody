package dev.cruz.services;

import dev.cruz.entities.Ticket;

import java.util.List;

public interface TicketService {
    //create
    Ticket createTicket(Ticket ticket);

    //read
    Ticket getTicketById(int id);
    List<Ticket> getAllTickets();

    //update
    Ticket updateTicket(Ticket ticket);

    //delete
    boolean deleteTicketById(int id);
}