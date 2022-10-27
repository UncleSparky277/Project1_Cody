package dev.cruz.controllers;

import com.google.gson.Gson;
import dev.cruz.entities.Ticket;
import dev.cruz.services.TicketServiceImpl;
import io.javalin.http.Handler;

import java.util.List;

public class TicketController {

    private TicketServiceImpl es;

    public TicketController(TicketServiceImpl es) {
        this.es = es;
    }

    public Handler createTicket = (ctx) ->{
        String json = ctx.body();
        Gson gson = new Gson();
        Ticket ticket = gson.fromJson(json, Ticket.class);
        Ticket registeredTicket = es.createTicket(ticket);
        String ticketJson = gson.toJson(registeredTicket);
        ctx.status(202); //This is a status code that will tell us how things went
        ctx.result(ticketJson);
    };

    public Handler getTicketByIdHandler = (ctx) ->{
        int id = Integer.parseInt(ctx.pathParam("id"));
        Ticket ticket = es.getTicketById(id);
        Gson gson = new Gson();
        String json = gson.toJson(ticket);
        ctx.result(json);
    };

    public Handler getAllTickets = (ctx) ->{
        List<Ticket> tickets = es.getAllTickets();
        Gson gson = new Gson();
        String json = gson.toJson(tickets);
        ctx.result(json);
    };

    public Handler updateTicketHandler = (ctx) ->{
        String ticketJSON = ctx.body();
        Gson gson = new Gson();
        Ticket ticket = gson.fromJson(ticketJSON, Ticket.class);
        Ticket updateTicket = es.updateTicket(ticket);
        String json = gson.toJson(updateTicket);
        ctx.result(json);
    };


    public Handler deleteTicketHandler = (ctx) ->{
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean result = es.deleteTicketById(id);
        if(result){
            ctx.status(202);
        }
        else{
            ctx.status(401);
            ctx.result("Could not process your delete request");
        }
    };



}
