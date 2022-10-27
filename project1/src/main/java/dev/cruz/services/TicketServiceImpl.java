package dev.cruz.services;

import dev.cruz.entities.Ticket;
import dev.cruz.exceptions.InvalidAdminException;
import dev.cruz.repositories.TicketDAO;
import java.util.List;

public class TicketServiceImpl implements TicketService {

    private TicketDAO ticketDAO;

    public TicketServiceImpl(TicketDAO ticketDAO){
        this.ticketDAO = ticketDAO;
    }



    @Override
    public Ticket createTicket(Ticket ticket) {

        if(ticket.getName().length() == 0){
            throw new RuntimeException("Ticket's name cannot be empty");
        }
        if(ticket.getDescription().length() == 0){
            throw new InvalidAdminException("Ticket's description cannot be empty");
        }
        if(ticket.getAmount().length() == 0){
            throw new InvalidAdminException("Ticket's amount cannot be empty");
        }
        Ticket savedTicket = this.ticketDAO.createTicket(ticket);
        return savedTicket;
    }

    @Override
    public Ticket getTicketById(int id) {

        Ticket ticket = this.ticketDAO.getTicketById(id);
        return ticket;

    }

    public List<Ticket> getAllTickets() {
        return this.ticketDAO.getAllTickets();
    }

    @Override
    public Ticket updateTicket(Ticket ticket) {
        if(ticket.getName().length() == 0){
            throw new RuntimeException("Ticket's name cannot be empty");
        }
        if(ticket.getDescription().length() == 0){
            throw new RuntimeException("Ticket's description cannot be empty");
        }
        if(ticket.getAmount().length() == 0){
            throw new RuntimeException("Ticket's amount cannot be empty");
        }
        return this.ticketDAO.updateTicket(ticket);
    }

    @Override
    public boolean deleteTicketById(int id) {
        return this.ticketDAO.deleteTicketById(id);
    }

}