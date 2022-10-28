package dev.cruz.services;

import dev.cruz.entities.Ticket;
import dev.cruz.exceptions.InvalidAdminException;
import dev.cruz.repositories.TicketDAO;
import java.util.List;

public class TicketServiceImpl implements TicketService {

    private final TicketDAO ticketDAO;

    public TicketServiceImpl(TicketDAO ticketDAO){
        this.ticketDAO = ticketDAO;
    }



    @Override
    public Ticket createTicket(Ticket ticket, int id) {

        if(ticket.getName().length() == 0){
            throw new RuntimeException("Ticket's name cannot be empty");
        }
        if(ticket.getDescription().length() == 0){
            throw new InvalidAdminException("Ticket's description cannot be empty");
        }
        if(ticket.getAmount() <= 0){
            throw new InvalidAdminException("Ticket's amount cannot be 0 or negative");
        }
        return ticketDAO.createTicket(ticket, id);
    }

    @Override
    public List<Ticket> getTicketById(int id) {

        return ticketDAO.getTicketById(id);

    }

    @Override
    public Ticket getTicketById1(int id) {

        return ticketDAO.getTicketById1(id);

    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketDAO.getAllTickets();
    }

    @Override
    public List<Ticket> getPendingTickets() { return ticketDAO.getPendingTickets(); }

    @Override
    public Ticket updateTicket(Ticket ticket) {
        if(ticket.getName().length() == 0){
            throw new RuntimeException("Ticket's name cannot be empty");
        }
        if(ticket.getDescription().length() == 0){
            throw new RuntimeException("Ticket's description cannot be empty");
        }
        if(ticket.getAmount() <= 0){
            throw new RuntimeException("Ticket's amount cannot be 0 or negative");
        }
        return ticketDAO.updateTicket(ticket);
    }

    @Override
    public boolean deleteTicketById(int id) {
        return ticketDAO.deleteTicketById(id);
    }

}