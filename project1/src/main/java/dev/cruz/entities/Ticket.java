package dev.cruz.entities;

import java.util.Objects;

public class Ticket {

    private int id;
    private String name;
    private String amount;
    private String description;
    private String status;


    public Ticket() {
    }

    public Ticket(String name, String amount, String description, String status) {
        this.name = name;
        this.amount = amount;
        this.description = description;
        this.status = status;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id && Objects.equals(name, ticket.name) && Objects.equals(amount, ticket.amount) && Objects.equals(description, ticket.description) && Objects.equals(status, ticket.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, amount, description, status);
    }
}
