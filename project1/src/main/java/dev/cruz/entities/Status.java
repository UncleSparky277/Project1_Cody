package dev.cruz.entities;


import dev.cruz.controllers.TicketController;
import dev.cruz.repositories.TicketDAO;
import dev.cruz.repositories.TicketDAOPostgres;
import dev.cruz.services.TicketService;
import dev.cruz.services.TicketServiceImpl;
import io.javalin.Javalin;

public enum Status {
    PENDING, APPROVED, DENIED;
}

