package dev.cruz.repositories;

import dev.cruz.entities.Ticket;
import dev.cruz.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicketDAOPostgres implements TicketDAO{

    @Override
    public Ticket createTicket(Ticket ticket) {

        try(Connection connection = ConnectionFactory.getConnection()){

            String sql = "insert into tickets (name, amount, description, status) values( ?, ? , ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, ticket.getName());
            preparedStatement.setString(2,ticket.getAmount());
            preparedStatement.setString(3,ticket.getDescription());
            preparedStatement.setString(4, ticket.getStatus());

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            int generatedKey = resultSet.getInt("id");
            ticket.setId(generatedKey);
            return ticket;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Ticket getTicketById(int id) {
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "select * from tickets where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();
            rs.next();

            Ticket ticket = new Ticket();
            ticket.setId(rs.getInt("id"));
            ticket.getName();
            ticket.getAmount();
            ticket.getDescription();
            ticket.getStatus();

            return ticket;

        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }



    }

    @Override
    public List<Ticket> getAllTickets() {
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "select * from tickets";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            List<Ticket> ticketList = new ArrayList();

            while(rs.next()){
                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt("id"));
                ticket.getName();
                ticket.getAmount();
                ticket.getDescription();
                ticket.getStatus();
                ticketList.add(ticket);
            }
            return ticketList;

        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Ticket updateTicket(Ticket ticket) {
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "Update tickets set name = ?, amount = ?, description = ?, status = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, ticket.getName());
            preparedStatement.setString(2,ticket.getAmount());
            preparedStatement.setString(3,ticket.getDescription());
            preparedStatement.setString(4, ticket.getStatus());
            preparedStatement.setInt(5, ticket.getId());

            preparedStatement.executeUpdate();
            return ticket;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }


    @Override
    public boolean deleteTicketById(int id) {
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "delete from tickets where id =?";
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
