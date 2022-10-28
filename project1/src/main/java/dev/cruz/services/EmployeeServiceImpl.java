package dev.cruz.services;

import dev.cruz.entities.Employee;
import dev.cruz.exceptions.InvalidEmailorPasswordException;
import dev.cruz.exceptions.InvalidFirstNameException;
import dev.cruz.repositories.EmployeeDAO;
import dev.cruz.exceptions.InvalidLastNameException;
import java.util.List;
import java.util.Objects;

public class EmployeeServiceImpl implements EmployeeService {
    //EmployeeDAO is the parent class of EmployeeDAOLocal, so when making an object of any child of EmployeeDAO, you can have EmployeeDAO on the right
    // and then you specify the specific child you want on the left. This is JUST to save time in the future when changing to a different DAO
    //private EmployeeDAO employeeDAO = new EmployeeDAOLocal();
    // ^^^ This is perfectly valid in regards to replacing Dependency Injection


    //We are going to use Dependency injection to create a employeeDAO with crud operations
    // This will build an instance of the service with the dependency in the constructor
    // This makes using our DAO much easier
    private final EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;
    }



    @Override
    public Employee createEmployee(Employee employee) {
        //Lets write business rules to validate our code (Business Logic)
       Employee creationTest= employeeDAO.getEmployeeByEmail(employee.getEmail());
       // if (creationTest != null){
         //   throw new RuntimeException("Duplicate email");
       // }
        if(employee.getFirstName().length() == 0){
            throw new InvalidFirstNameException("Employee's first name cannot be empty");
        }
        if(employee.getLastName().length() == 0){
            throw new InvalidLastNameException("Employee's last name cannot be empty");
        }
        return employeeDAO.createEmployee(employee);
    }

    @Override
    public Employee getEmployeeById(int id) {
        //we COULD do this
        return employeeDAO.getEmployeeById(id);
        //  ^  v The upper 2 lines vs the lower 1 line do the same thing and are both valid
       // return this.employeeDAO.getEmployeeById(id);
    }

    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        if(employee.getFirstName().length() == 0){
            throw new InvalidFirstNameException("Employee's first name cannot be empty");
        }
        if(employee.getLastName().length() == 0){
            throw new InvalidLastNameException("Employee's last name cannot be empty");
        }
        return employeeDAO.updateEmployee(employee);
    }

    @Override
    public boolean deleteEmployeeById(int id) {
        return employeeDAO.deleteEmployeeById(id);
    }

    @Override
    public Employee loginEmployee(String email, String password){
       Employee loginEmployee = employeeDAO.getEmployeeByEmail(email);
       System.out.println(loginEmployee);
       System.out.println("email: "  + email + "\n" + "password: " + password + "\n" + "loginPass: " + loginEmployee.getPassword());
       if (Objects.equals(password, loginEmployee.getPassword())) {
           return loginEmployee;
       } else {
           throw new InvalidEmailorPasswordException("Email or Password is incorrect");
       }
    }

}
