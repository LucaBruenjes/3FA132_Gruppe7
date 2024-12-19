import dev.hv.model.ICustomer;

import java.sql.*;
import java.time.LocalDate;
import java.util.UUID;

public class DAOCustomer {
    private Connection connection;
    Statement stmt;
    UUID customerId;
    Date birthDate;
    String firstName;
    String lastName;
    ICustomer.Gender gender;


    public DAOCustomer(Date birthDate, String firstName, String lastName, ICustomer.Gender gender){
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public DAOCustomer(Connection connection) {
        this.connection = connection;
    }

    public DAOCustomer getCustomer(UUID customerId) throws SQLException {

        DAOCustomer customer = null;
        String query = "SELECT birth_date, first_name, last_name, gender FROM Customer WHERE id = " + customerId + ")";

        try (ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {

                Object d = rs.getDate("birth_date");
                Object f = rs.getString("first_name");
                Object l = rs.getString("last_name");
                Object g = rs.getObject("gender");

            }


               customer = new DAOCustomer(
                        rs.getDate("birth_date"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getObject("gender")
                );
            }
        return customer;
    }

/*
    @Override
    public UUID getId() {
        return customerId;
    }

    @Override
    public LocalDate getBirthDate() throws SQLException {
        stmt.executeQuery("SELECT birth_date FROM Customer WHERE id = " + customerId + ")");
        return null;
    }

    @Override
    public String getFirstName() throws SQLException {
        stmt.executeQuery("SELECT first_name FROM Customer WHERE id = " + customerId + ")");
        return "";
    }

    @Override
    public Gender getGender() throws SQLException {
        stmt.executeQuery("SELECT gender FROM Customer WHERE id = " + customerId + ")");
        return null;
    }

    @Override
    public String getLastName() throws SQLException {
        stmt.executeQuery("SELECT last_name FROM Customer WHERE customer_id = " + customerId + ")");
        return "";
    }

    @Override
    public void setId(UUID id) throws SQLException {
        stmt.executeQuery("INSERT INTO Customer (id) VALUES (" + id + ")");
        customerId = id;
    }

    @Override
    public void setBirthDate(LocalDate birtDate) throws SQLException {
        stmt.executeQuery("INSERT INTO Customer (birth_dates) VALUES (" + birtDate + ")  WHERE customer_id = " + customerId + ")");
    }

    @Override
    public void setFirstName(String firstName) throws SQLException {
        stmt.executeQuery("INSERT INTO Customer (first_name) VALUES (" + firstName + ") WHERE customer_id = " + customerId + ")");
    }

    @Override
    public void setGender(Gender gender) throws SQLException {
        stmt.executeQuery("INSERT INTO Customer (gender) VALUES (" + gender + ") WHERE customer_id = " + customerId + ")");
    }

    @Override
    public void setLastName(String lastName) throws SQLException {
        stmt.executeQuery("INSERT INTO Customer (last_name) VALUES (" + lastName + ") WHERE customer_id = " + customerId + ")");
    }*/
}
