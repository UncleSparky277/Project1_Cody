package dev.cruz.repositories;

import dev.cruz.entities.Employee;
import dev.cruz.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeDAOPostgres implements EmployeeDAO{

    @Override
    public Employee createEmployee(Employee employee) {
        // try with resources. This will create our connection and end the connection when the try block is over
        // or if something fails, it will end after the catch
        try(Connection connection = ConnectionFactory.getConnection()){
            // Here is the unfun thing about JDBC, you have to write SQL statements in Java
            // I recommend putting in comments the SQL command you are trying to execute
            //INSERT INTO employees VALUES (DEFAULT, 'Great Gatsby', 'F. Scott Fitts Jerald', 0);
            String sql = "insert into employees (firstname, lastname, address, \"password\", email, isadmin, status) values( ?, ? , ?, ?, ?, ?, ?)";
            // The only thing in the sql String that isnt "just a string" are the question marks
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // We use Return generated Keys, to get back the primary key of our newly created employee
            //Parameters START at 1, they are not indexed at 0
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2,employee.getLastName());
            preparedStatement.setString(3,employee.getAddress());
            preparedStatement.setString(4, employee.getPassword());
            preparedStatement.setString(5, employee.getEmail());
            preparedStatement.setBoolean(6, employee.isAdmin());
            preparedStatement.setString(7, employee.getStatus());

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();//this returns the id that was created
            resultSet.next();//you need to move the cursor to the first valid record, or you will get a null response
            int generatedKey = resultSet.getInt("id");
            employee.setId(generatedKey);
            return employee;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Employee getEmployeeById(int id) {
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "select * from employees where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();
            rs.next();

            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.getFirstName();
            employee.getLastName();
            employee.getAddress();
            employee.getPassword();
            employee.getEmail();
            employee.isAdmin();
            employee.getStatus();

            return employee;

        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }



    }

    @Override
    public List<Employee> getAllEmployees() {
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "select * from employees";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            List<Employee> employeeList = new ArrayList();

            while(rs.next()){
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.getFirstName();
                employee.getLastName();
                employee.getAddress();
                employee.getPassword();
                employee.getEmail();
                employee.isAdmin();
                employee.getStatus();
                employeeList.add(employee);
            }
            return employeeList;

        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "Update employees set firstName = ?, lastName = ?, address = ?, password = ?, email = ?, isAdmin = ?, status = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2,employee.getLastName());
            preparedStatement.setString(3,employee.getAddress());
            preparedStatement.setString(4, employee.getPassword());
            preparedStatement.setString(5, employee.getEmail());
            preparedStatement.setBoolean(6, employee.isAdmin());
            preparedStatement.setString(7, employee.getStatus());
            preparedStatement.setInt(8, employee.getId());

            preparedStatement.executeUpdate();
            return employee;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }


    @Override
    public boolean deleteEmployeeById(int id) {
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "delete from employees where id =?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1,id);

            preparedStatement.execute();
            return true;
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }



}
