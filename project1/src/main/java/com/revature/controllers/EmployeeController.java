package com.revature.controllers;

public class EmployeeController {

    public Handler createEmployee = (ctx) ->{
        String json = ctx.body();
        Gson gson = new Gson();
        Employee employee = gson.fromJson(json, Employee.class);

    }
}
