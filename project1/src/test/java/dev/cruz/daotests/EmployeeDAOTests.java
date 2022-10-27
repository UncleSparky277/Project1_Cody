package dev.cruz.daotests;

import dev.cruz.entities.Employee;
import dev.cruz.entities.Status;
import dev.cruz.repositories.EmployeeDAO;
import dev.cruz.repositories.EmployeeDAOLocal;
import dev.cruz.repositories.EmployeeDAOPostgres;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeDAOTests {

    static EmployeeDAO employeeDAO = new EmployeeDAOPostgres();

    // this is kabob case, tests are written in kabob case
    @Test
    @Order(1)
    void create_employee_test(){
        Employee newEmployee = new Employee(0,"firstName","lastName", "123 address st", "password", "user", false, "APPROVED");
        Employee savedEmployee = employeeDAO.createEmployee(newEmployee);
        Assertions.assertNotEquals(0,savedEmployee.getId());
    }
    @Test
    @Order(2)
    void get_employee_by_id_test(){
        Employee gottenEmployee = employeeDAO.getEmployeeById(1);
        Assertions.assertEquals("lastName",gottenEmployee.getLastName());
    }
    @Test
    @Order(3)
    void update_employee_test(){
        //When testing update, you should either get the employee and use its values or create a completely new employee and use those values
        Employee employee = employeeDAO.getEmployeeById(1);
        //You can think of update more of a full replacement/swap and less of specific values being update
        Employee employeeV2 = new Employee(employee.getId(),employee.getFirstName(), employee.getLastName(), employee.getAddress(), employee.getPassword(), employee.getEmail(), employee.isAdmin(), employee.getStatus());
        employeeDAO.updateEmployee(employeeV2);
        Employee updatedEmployee = employeeDAO.getEmployeeById(employeeV2.getId());
        Assertions.assertEquals("lastName",updatedEmployee.getLastName());
    }
    @Test
    @Order(4)
    void delete_employee_by_id_test(){
        boolean result = employeeDAO.deleteEmployeeById(1);
       // Assertions.assertTrue(result);
    }

}
