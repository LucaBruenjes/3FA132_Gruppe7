import dev.hv.model.Customer;
import dev.hv.model.ICustomer;

import java.sql.*;
import java.util.UUID;

public class DAOCustomer {

    public ICustomer createCustomer(ICustomer customer) {
        String sql = "INSERT INTO customers (id, first_name, last_name, birth_date, gender) VALUES (?,?,?,?,?)";
        DatabaseConnection databaseConnection = new DatabaseConnection();
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            UUID newId = (customer.getId() == null) ? UUID.randomUUID() : customer.getId();
            stmt.setString(1, newId.toString());
            stmt.setString(2, customer.getFirstName());
            stmt.setString(3, customer.getLastName());
            stmt.setDate(4, customer.getBirthDate() != null ? Date.valueOf(customer.getBirthDate()) : null);
            stmt.setString(5, customer.getGender() != null ? customer.getGender().name() : null);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                customer.setId(newId);
                return customer;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public ICustomer findById(UUID id) {
        String sql = "SELECT id, first_name, last_name, birth_date, gender FROM customers WHERE id = ?";
        DatabaseConnection databaseConnection = new DatabaseConnection();
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null; // Falls kein Kunde gefunden wurde
    }
}
