package dev.hv.dao;

import dev.hv.model.Customer;
import dev.hv.model.ICustomer;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

public class DAOCustomer {

    public void createCustomer(ICustomer customer) throws RuntimeException {
        String sql = "INSERT INTO customers (id, first_name, last_name, birth_date, gender) VALUES (?,?,?,?,?)";
        DatabaseConnection databaseConnection = new DatabaseConnection();
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            UUID newId = (customer.getId() == null) ? UUID.randomUUID() : customer.getId();
            stmt.setString(1, newId.toString());
            stmt.setString(2, customer.getFirstName());
            stmt.setString(3, customer.getLastName());
            stmt.setDate(4, Date.valueOf(customer.getBirthDate()));
            stmt.setString(5, customer.getGender() != null ? customer.getGender().name() : null);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ICustomer findById(UUID id) {
        String sql = "SELECT id, first_name, last_name, birth_date, gender FROM customers WHERE id = ?";
        DatabaseConnection databaseConnection = new DatabaseConnection();
        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, id.toString());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ICustomer customer = new Customer();
                customer.setId(UUID.fromString(rs.getString("id")));
                customer.setFirstName(rs.getString("first_name"));
                customer.setLastName(rs.getString("last_name"));
                customer.setBirthDate(rs.getDate("birth_date").toLocalDate());
                customer.setGender(ICustomer.Gender.valueOf(rs.getString("gender")));
                return customer;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Customer> getCustomerList() throws RuntimeException {
        String sql = "SELECT id, first_name, last_name, birth_date, gender FROM customers";
        DatabaseConnection databaseConnection = new DatabaseConnection();
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            ArrayList<Customer> list = new ArrayList<>();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(UUID.fromString(rs.getString("id")));
                customer.setFirstName(rs.getString("first_name"));
                customer.setLastName(rs.getString("last_name"));
                customer.setBirthDate(rs.getDate("birth_date").toLocalDate());
                customer.setGender(ICustomer.Gender.valueOf(rs.getString("gender")));
                list.add(customer);
            }
            rs.close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCustomer(ICustomer customer) {
        String sql = "UPDATE customers SET first_name = ?, last_name = ?, birth_date = ?, gender = ? WHERE id = ?";
        DatabaseConnection databaseConnection = new DatabaseConnection();
        try (Connection connection = databaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, customer.getFirstName());
            stmt.setString(2, customer.getLastName());
            stmt.setDate(3, customer.getBirthDate() != null ? Date.valueOf(customer.getBirthDate()) : null);
            stmt.setString(4, customer.getGender() != null ? customer.getGender().name() : null);
            stmt.setString(5, customer.getId().toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(UUID id) throws RuntimeException {
        String sqlReading = "UPDATE reading SET customer_id = null WHERE customer_id = ?";
        String sqlCustomer = "DELETE FROM customer WHERE id = ?";
        DatabaseConnection databaseConnection = new DatabaseConnection();
        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sqlCustomer);
            stmt.setString(1, id.toString());
            stmt.executeUpdate();
            PreparedStatement stmtReading = connection.prepareStatement(sqlReading);
            stmtReading.setString(1, id.toString());
            stmtReading.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
