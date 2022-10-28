package dev.cruz.entities;

import java.util.Objects;

public class Ticket {

    private int id;
    private String name;
    private int amount;
    private String description;
    private String status;


    private int employeeId;


    public Ticket() {
    }

    public Ticket(String name, int amount, String description, String status, int employeeId) {
        this.name = name;
        this.amount = amount;
        this.description = description;
        this.status = status;
        this.employeeId = employeeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount='" + amount + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", employeeId=" + employeeId +
                '}';
    }

}
