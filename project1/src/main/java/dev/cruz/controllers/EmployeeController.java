package dev.cruz.controllers;

import com.google.gson.Gson;
import dev.cruz.entities.Employee;
import dev.cruz.entities.LoginObject;
import dev.cruz.services.EmployeeServiceImpl;
import io.javalin.http.Handler;

import java.util.List;

public class EmployeeController {

    private EmployeeServiceImpl es;

    public EmployeeController(EmployeeServiceImpl es) {
        this.es = es;
    }

    public Handler handleRegister = (ctx) -> {
        String json = ctx.body();
        Gson gson = new Gson();
        Employee employee = gson.fromJson(json, Employee.class);
        Employee registeredEmployee = es.createEmployee(employee);
        String employeeJson = gson.toJson(registeredEmployee);
        ctx.status(202); //This is a status code that will tell us how things went
        ctx.result(employeeJson + "Registered");

       // es.createEmployee(u);
//        ctx.status(201);
//        ctx.result("Registered user" + employeeJson);
    };
    public Handler handleLogin = (ctx) -> {


        Gson gson = new Gson();

        LoginObject lo = gson.fromJson(ctx.body(), LoginObject.class);
        Employee e = es.loginEmployee(lo.getEmail(), lo.getPassword());
        if(e == null){
            ctx.status(403);
            ctx.result("Email or password was incorrect");
        } else {
            //We could also, if the user is logged in successfully, set up a session for them
            ctx.req.getSession().setAttribute("loggedIn", ""+e.getEmail());
            ctx.req.getSession().setAttribute("isAdmin", ""+ e.isAdmin());
            ctx.req.getSession().setAttribute("Id", ""+e.getId());
            ctx.result(gson.toJson(e, Employee.class));
        }
    };

    public Handler handleLogout = (ctx) -> {
        ctx.req.getSession().invalidate();
        ctx.result("You logged out");
    };


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
