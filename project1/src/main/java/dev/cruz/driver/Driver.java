package dev.cruz.driver;

import dev.cruz.controllers.EmployeeController;
import dev.cruz.controllers.TicketController;
import dev.cruz.repositories.*;
import dev.cruz.services.EmployeeService;
import dev.cruz.services.EmployeeServiceImpl;
import dev.cruz.services.TicketService;
import dev.cruz.services.TicketServiceImpl;
import io.javalin.Javalin;

public class Driver {
    // Because I am using dependency injection in my Service, I need to add my employeeDAO as an argument when making a static object
    // If you make a static object of your Service, you can use this single instance throughout your application
    public static EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDAOPostgres());
    public static TicketService ticketService = new TicketServiceImpl(new TicketDAOPostgres());
    public static void main(String[] args) {
        Javalin app = Javalin.create();
        EmployeeDAO employeeDAO = new EmployeeDAOPostgres();
        EmployeeServiceImpl es = new EmployeeServiceImpl(employeeDAO);
        EmployeeController employeeController = new EmployeeController(es);





        app.get("/employees/{id}", employeeController.getEmployeeByIdHandler);

        app.get("/employees", employeeController.getAllEmployees);

        app.post("/employees", employeeController.createEmployee);

        app.put("/employees", employeeController.updateEmployeeHandler);

        app.delete("/employees/{id}", employeeController.deleteEmployeeHandler);





    {


        TicketDAO ticketDAO = new TicketDAOPostgres();
        TicketServiceImpl os = new TicketServiceImpl(ticketDAO);
        TicketController ticketController = new TicketController(os);





        app.get("/tickets/{id}", ticketController.getTicketByIdHandler);

        app.get("/tickets", ticketController.getAllTickets);

        app.post("/tickets", ticketController.createTicket);

        app.put("/tickets", ticketController.updateTicketHandler);

        app.delete("/tickets/{id}", ticketController.deleteTicketHandler);



        app.start();
    }
    }
}
