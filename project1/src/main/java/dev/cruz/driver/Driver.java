package dev.cruz.driver;

import dev.cruz.controllers.EmployeeController;
import dev.cruz.handlers.*;
import dev.cruz.repositories.EmployeeDAO;
import dev.cruz.repositories.EmployeeDAOLocal;
import dev.cruz.repositories.EmployeeDAOPostgres;
import dev.cruz.services.EmployeeService;
import dev.cruz.services.EmployeeServiceImpl;
import io.javalin.Javalin;

public class Driver {
    // Because I am using dependency injection in my Service, I need to add my employeeDAO as an argument when making a static object
    // If you make a static object of your Service, you can use this single instance throughout your application
    public static EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDAOPostgres());
    public static void main(String[] args) {
        Javalin app = Javalin.create();

        HelloHandler helloHandler = new HelloHandler();
//        GetEmployeeByIdHandler getEmployeeByIdHandler = new GetEmployeeByIdHandler();
//        CreateEmployeeHandler createEmployeeHandler = new CreateEmployeeHandler();
//        UpdateEmployeeHandler updateEmployeeHandler = new UpdateEmployeeHandler();
//        DeleteEmployeeHandler deleteEmployeeHandler = new DeleteEmployeeHandler();
        EmployeeController employeeController = new EmployeeController();


        app.get("/hello", helloHandler);

        app.get("/employees/{id}", employeeController.getEmployeeByIdHandler);

        app.get("/employees", employeeController.getAllEmployees);

        app.post("/employees", employeeController.createEmployee);

        app.put("/employees", employeeController.updateEmployeeHandler);

        app.delete("/employees/{id}", employeeController.deleteEmployeeHandler);



        app.start();
    }
}
