package dev.cruz.driver;

import dev.cruz.controllers.EmployeeController;
import dev.cruz.controllers.TicketController;
import dev.cruz.repositories.*;
import dev.cruz.services.EmployeeServiceImpl;
import dev.cruz.services.TicketServiceImpl;
import io.javalin.Javalin;

public class Driver {
    // Because I am using dependency injection in my Service, I need to add my employeeDAO as an argument when making a static object
    // If you make a static object of your Service, you can use this single instance throughout your application
    public static void main(String[] args) {

        //Start's Javalin app
        Javalin app = Javalin.create();
        //setup for Employee Controller
        EmployeeDAO employeeDAO = new EmployeeDAOPostgres();
        EmployeeServiceImpl es = new EmployeeServiceImpl(employeeDAO);
        EmployeeController employeeController = new EmployeeController(es);
        //setup for Ticket Controller
        TicketDAO ticketDAO = new TicketDAOPostgres();
        TicketServiceImpl os = new TicketServiceImpl(ticketDAO);
        TicketController ticketController = new TicketController(os);

        //Employee Routes
        app.get("/employees/{id}", employeeController.getEmployeeByIdHandler); //gets an employee by id

        app.get("/employees", employeeController.getAllEmployees); // gets all employees

        app.post("/employees", employeeController.createEmployee); // creates a new employee

        app.put("/employees", employeeController.updateEmployeeHandler); // updates an employee

        app.delete("/employees/{id}", employeeController.deleteEmployeeHandler); // deletes employee by id

        app.post("/login", employeeController.handleLogin); // login with email/pass - creates new session

        app.post("/register", employeeController.handleRegister); // register new employee - still need to log in

        app.post("/logout", employeeController.handleLogout); // delete the current session

        //Ticket Routes
        app.get("/tickets/{id}", ticketController.getTicketByIdAdmin); // get all ticket by employee id for admins

        app.get("/employee/tickets/", ticketController.getTicketByIdEmployee); // get all ticket by employee id for employee

        app.get("/tickets", ticketController.getAllTickets);// get all tickets

        app.get("/pending", ticketController.getAllPendingTickets); //gets all pending tickets

        app.post("/tickets", ticketController.createTicket); // create new ticket

        app.put("/tickets", ticketController.updateTicketHandler); // updates ticket by id - use for APPROVE/DENY

        app.delete("/tickets/{id}", ticketController.deleteTicketHandler);// deletes ticket by id

        //starts Javelin Server
        app.start(8080);
    }
}
