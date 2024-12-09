import dev.hv.model.ICustomer;

import java.sql.*;
import java.time.LocalDate;
import java.util.UUID;

public abstract class Customer implements ICustomer{
    Statement stmt;
    UUID customerId;

    public Customer(UUID id) throws SQLException {
        customerId = id;

        Connection con = DriverManager.getConnection(
                System.getProperty("url.name"),
                System.getProperty("user.name"),
                System.getProperty("pw.name"));
        stmt = con.createStatement();
    }

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
    }
}
