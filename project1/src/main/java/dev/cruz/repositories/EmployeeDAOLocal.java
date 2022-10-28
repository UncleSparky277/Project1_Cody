package dev.cruz.repositories;

import dev.cruz.entities.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDAOLocal implements EmployeeDAO{

    private Map<Integer,Employee> employeeTable = new HashMap();
    private int idCount = 1;


    @Override
    public Employee createEmployee(Employee employee) {
        employee.setId(idCount);
        idCount++;
        employeeTable.put(employee.getId(),employee);
        System.out.println(employeeTable.values());
        return employee;
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeTable.get(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return null;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeTable.put(employee.getId(), employee); //This overwrites that spot in the map
        //put return the employee that you replaced
    }

    @Override
    public boolean deleteEmployeeById(int id) {
        Employee employee = employeeTable.remove(id); //This removes method return the object that was removed from the map
        if(employee == null){
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        return null;
    }
}
