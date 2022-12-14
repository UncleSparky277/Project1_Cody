package dev.cruz.repositories;

import dev.cruz.entities.Ticket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketDAOLocal implements TicketDAO{

    private Map<Integer,Ticket> ticketTable = new HashMap();
    private int idCount = 1;


    @Override
    public Ticket createTicket(Ticket ticket, int id) {
        ticket.setId(idCount);
        idCount++;
        ticketTable.put(ticket.getId(),ticket);
        System.out.println(ticketTable.values());
        return ticket;
    }

    @Override
    public List<Ticket> getTicketById(int id) {
        return null;
    }

    @Override
    public Ticket getTicketById1(int id) {
        return null;
    }

    @Override
    public List<Ticket> getAllTickets() {
        return null;
    }

    @Override
    public List<Ticket> getPendingTickets() {
        return null;
    }

    @Override
    public Ticket updateTicket(Ticket ticket) {
        return ticketTable.put(ticket.getId(), ticket);
    }

    @Override
    public boolean deleteTicketById(int id) {
        Ticket ticket = ticketTable.remove(id);
        if(ticket == null){
            return false;
        }
        else {
            return true;
        }
    }
}
