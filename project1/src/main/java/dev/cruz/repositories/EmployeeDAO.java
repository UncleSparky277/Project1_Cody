package dev.cruz.repositories;

import dev.cruz.entities.Employee;

import java.util.List;

public interface EmployeeDAO {

    //create
    Employee createEmployee(Employee employee);

    //read
    Employee getEmployeeById(int id);
    List<Employee> getAllEmployees();

    //update
    Employee updateEmployee(Employee employee);

    //delete
    boolean deleteEmployeeById(int id);
}
