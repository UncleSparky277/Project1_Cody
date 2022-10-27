package dev.cruz.controllers;

import com.google.gson.Gson;
import dev.cruz.driver.Driver;
import dev.cruz.entities.Employee;
import dev.cruz.services.EmployeeService;
import dev.cruz.services.EmployeeServiceImpl;
import io.javalin.http.Handler;

import java.util.List;

public class EmployeeController {

    private EmployeeServiceImpl es;

    public EmployeeController(EmployeeServiceImpl es) {
        this.es = es;
    }

  public Handler createEmployee = (ctx) ->{
      String json = ctx.body();
      Gson gson = new Gson();
      Employee employee = gson.fromJson(json, Employee.class);
      Employee registeredEmployee = es.createEmployee(employee);
      String employeeJson = gson.toJson(registeredEmployee);
      ctx.status(202); //This is a status code that will tell us how things went
      ctx.result(employeeJson);
  };

    public Handler getEmployeeByIdHandler = (ctx) ->{
        int id = Integer.parseInt(ctx.pathParam("id"));//This will take what value was in the {id} and turn it into an int for us to use
        Employee employee = es.getEmployeeById(id);
        Gson gson = new Gson();
        String json = gson.toJson(employee);
        ctx.result(json);
    };

    public Handler getAllEmployees = (ctx) ->{
        List<Employee> employees = es.getAllEmployees();
        Gson gson = new Gson();
        String json = gson.toJson(employees);
        ctx.result(json);
    };

    public Handler updateEmployeeHandler = (ctx) ->{
        String employeeJSON = ctx.body();
        Gson gson = new Gson();
        Employee employee = gson.fromJson(employeeJSON, Employee.class);
        Employee updateEmployee = es.updateEmployee(employee);
        String json = gson.toJson(updateEmployee);
        ctx.result(json);
    };


    public Handler deleteEmployeeHandler = (ctx) ->{
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean result = es.deleteEmployeeById(id);
        if(result){
            ctx.status(202);
        }
        else{
            ctx.status(401);
            ctx.result("Could not process your delete request");
        }
  };
}
