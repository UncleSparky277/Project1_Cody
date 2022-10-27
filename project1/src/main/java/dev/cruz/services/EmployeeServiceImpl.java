package dev.cruz.services;

import dev.cruz.entities.Employee;
import dev.cruz.repositories.EmployeeDAO;
import dev.cruz.exceptions.InvalidLastNameException;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    //EmployeeDAO is the parent class of EmployeeDAOLocal, so when making an object of any child of EmployeeDAO, you can have EmployeeDAO on the right
    // and then you specify the specific child you want on the left. This is JUST to save time in the future when changing to a different DAO
    //private EmployeeDAO employeeDAO = new EmployeeDAOLocal();
    // ^^^ This is perfectly valid in regards to replacing Dependency Injection


    //We are going to use Dependency injection to create a employeeDAO with crud operations
    // This will build an instance of the service with the dependency in the constructor
    // This makes using our DAO much easier
    private EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;
    }



    @Override
    public Employee createEmployee(Employee employee) {
        //Lets write business rules to validate our code (Buisness Logic)
        if(employee.getFirstName().length() == 0){
            throw new RuntimeException("Employee's title cannot be empty");
        }
        if(employee.getLastName().length() == 0){
            throw new InvalidLastNameException("Employee's authors cannot be empty");
        }
        Employee savedEmployee = this.employeeDAO.createEmployee(employee);
        return savedEmployee;
    }

    @Override
    public Employee getEmployeeById(int id) {
        //we COULD do this
         Employee employee = this.employeeDAO.getEmployeeById(id);
         return employee;
        //  ^  v The upper 2 lines vs the lower 1 line do the same thing and are both valid
       // return this.employeeDAO.getEmployeeById(id);
    }

    public List<Employee> getAllEmployees() {
        return this.employeeDAO.getAllEmployees();
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        if(employee.getFirstName().length() == 0){
            throw new RuntimeException("Employee's first cannot be empty");
        }
        if(employee.getLastName().length() == 0){
            throw new RuntimeException("Employee's last name cannot be empty");
        }
        return this.employeeDAO.updateEmployee(employee);
    }

    @Override
    public boolean deleteEmployeeById(int id) {
        return this.employeeDAO.deleteEmployeeById(id);
    }

}
