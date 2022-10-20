package dev.cruz.controllers;

import com.google.gson.Gson;
import dev.cruz.driver.Drive;
import dev.cruz.entities.Employee;
import io.javalin.http.Handler;

import java.util.List;

public class EmployeeController {

  public Handler createEmployee = (ctx) ->{
      String json = ctx.body();
     Gson gson = new Gson();
      Employee employee = gson.fromJson(json, Employee.class);

    }
}
