package dev.cruz.daotests;

import dev.cruz.entities.Ticket;
import dev.cruz.entities.Status;
import dev.cruz.repositories.TicketDAO;
import dev.cruz.repositories.TicketDAOLocal;
import dev.cruz.repositories.TicketDAOPostgres;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TicketDAOTests {

    static TicketDAO ticketDAO = new TicketDAOPostgres();

    // this is kabob case, tests are written in kabob case
    @Test
    @Order(1)
    void create_ticket_test(){
        Ticket newTicket = new Ticket(0,"name","amount", "description", "status");
        Ticket savedTicket = ticketDAO.createTicket(newTicket);
        Assertions.assertNotEquals(0,savedTicket.getId());
    }
    @Test
    @Order(2)
    void get_ticket_by_id_test(){
        Ticket gottenTicket = ticketDAO.getTicketById(1);
        Assertions.assertEquals("lastName",gottenTicket.getLastName());
    }
    @Test
    @Order(3)
    void update_ticket_test(){
        //When testing update, you should either get the ticket and use its values or create a completely new ticket and use those values
        Ticket ticket = ticketDAO.getTicketById(1);
        //You can think of update more of a full replacement/swap and less of specific values being update
        Ticket ticketV2 = new Ticket(ticket.getId(),ticket.getName(), ticket.getAmount(), ticket.getDescription(), ticket.getStatus());
        ticketDAO.updateTicket(ticketV2);
        Ticket updatedTicket = ticketDAO.getTicketById(ticketV2.getId());
        Assertions.assertEquals("lastName",updatedTicket.getLastName());
    }
    @Test
    @Order(4)
    void delete_ticket_by_id_test(){
        boolean result = ticketDAO.deleteTicketById(1);
        // Assertions.assertTrue(result);
    }

}
