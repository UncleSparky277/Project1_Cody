package dev.cruz.controllers;

import com.google.gson.Gson;
import dev.cruz.entities.Ticket;
import dev.cruz.services.TicketServiceImpl;
import io.javalin.http.Handler;

import java.util.List;
import java.util.Objects;

public class TicketController {

    private TicketServiceImpl os;

    public TicketController(TicketServiceImpl os) {
        this.os = os;
    }

    public Handler createTicket = (ctx) ->{
        if (Objects.equals(String.valueOf(ctx.req.getSession().getAttribute("loggedIn")), null)) {
            ctx.status(401);
            ctx.result("You must login as employee to create a ticket");
        } else {
            int id = Integer.parseInt(ctx.req.getSession().getAttribute("Id").toString());
            System.out.println("Id: " + id + "\n" + "ctx id: " +  Integer.parseInt(ctx.req.getSession().getAttribute("Id").toString()));
            String json = ctx.body();
            Gson gson = new Gson();
            Ticket ticket = gson.fromJson(json, Ticket.class);
            Ticket registeredTicket = os.createTicket(ticket, id);
            String ticketJson = gson.toJson(registeredTicket);
            Ticket returnTicket = os.getTicketById1(registeredTicket.getId());
            ctx.status(202);
            ctx.result(ticketJson);
        }
    };

    public Handler getTicketByIdAdmin = (ctx) ->{
        if (Objects.equals(String.valueOf(ctx.req.getSession().getAttribute("isAdmin")), "false") || Objects.equals(String.valueOf(ctx.req.getSession().getAttribute("isAdmin")), null)) {
            ctx.status(401);
            System.out.println(ctx.req.getSession().getAttribute("isAdmin"));
            ctx.result("You must login as admin to retrieve a ticket");
        }
        if (Objects.equals(String.valueOf(ctx.req.getSession().getAttribute("isAdmin")), "true")) {
            int id = Integer.parseInt(ctx.pathParam("id"));
            List<Ticket> tickets = os.getTicketById(id);
            Gson gson = new Gson();
            String json = gson.toJson(tickets);
            ctx.result(json);
        }
    };

    public Handler getTicketByIdEmployee = (ctx) ->{
        if (Objects.equals(String.valueOf(ctx.req.getSession().getAttribute("loggedIn")), null)) {
            ctx.status(401);
            ctx.result("You must login as employee to retrieve a ticket");
        } else {
            int id = Integer.parseInt(String.valueOf(ctx.req.getSession().getAttribute("Id")));
            List<Ticket> tickets = os.getTicketById(id);
            Gson gson = new Gson();
            String json = gson.toJson(tickets);
            ctx.result(json);
        }
    };

    public Handler getAllTickets = (ctx) ->{
        if (!Objects.equals(String.valueOf(ctx.req.getSession().getAttribute("isAdmin")), "false") || !Objects.equals(String.valueOf(ctx.req.getSession().getAttribute("isAdmin")), null)) {
            ctx.status(401);
            System.out.println(ctx.req.getSession().getAttribute("isAdmin"));
            ctx.result("You must login as Admin to get all tickets");
        }
        if (Objects.equals(String.valueOf(ctx.req.getSession().getAttribute("isAdmin")), "true")) {
            List<Ticket> tickets = os.getAllTickets();
            System.out.println(ctx.req.getSession().getAttribute("isAdmin"));
            Gson gson = new Gson();
            String json = gson.toJson(tickets);
            ctx.result(json);
        }
    };

    public Handler getAllPendingTickets = (ctx) ->{
        if (!Objects.equals(String.valueOf(ctx.req.getSession().getAttribute("isAdmin")), "false") || !Objects.equals(String.valueOf(ctx.req.getSession().getAttribute("isAdmin")), null)) {
            ctx.status(401);
            ctx.result("You must login as Admin to get all tickets");
        }
        if (Objects.equals(String.valueOf(ctx.req.getSession().getAttribute("isAdmin")), "true")) {
            List<Ticket> tickets = os.getPendingTickets();
            Gson gson = new Gson();
            String json = gson.toJson(tickets);
            ctx.result(json);
        }
    };

    public Handler updateTicketHandler = (ctx) ->{
        if (!Objects.equals(String.valueOf(ctx.req.getSession().getAttribute("isAdmin")), "false") || !Objects.equals(String.valueOf(ctx.req.getSession().getAttribute("isAdmin")), null)) {
            ctx.status(401);
            ctx.result("You must login as admin to update a ticket");
        }
        if (Objects.equals(String.valueOf(ctx.req.getSession().getAttribute("isAdmin")), "true")) {
            String ticketJSON = ctx.body();
            Gson gson = new Gson();
            Ticket ticket = gson.fromJson(ticketJSON, Ticket.class);
            Ticket updateTicket = os.updateTicket(ticket);
            String json = gson.toJson(updateTicket);
            ctx.result(json);
        }
    };


    public Handler deleteTicketHandler = (ctx) ->{
        if (!Objects.equals(String.valueOf(ctx.req.getSession().getAttribute("isAdmin")), "false") || !Objects.equals(String.valueOf(ctx.req.getSession().getAttribute("isAdmin")), null)) {
            ctx.status(401);
            ctx.result("You must login as admin to delete a ticket");
        }
        if (Objects.equals(String.valueOf(ctx.req.getSession().getAttribute("isAdmin")), "true")) {
        int id = Integer.parseInt(ctx.pathParam("Id"));
        boolean result = os.deleteTicketById(id);
        if(result){
            ctx.status(202);
        }
        else{
            ctx.status(401);
            ctx.result("Could not process your delete request");
        }
        }
    };



}
