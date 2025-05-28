package dev.hv.dao;

import dev.hv.model.Customer;
import dev.hv.model.ICustomer;
import dev.hv.model.IReading;
import dev.hv.model.Reading;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class DAOReading {

    public IReading createReading(IReading reading) throws RuntimeException {
        String sql = "INSERT INTO reading (id, meter_id, kind_of_meter, meter_count, date_of_reading, substitute, comment, customer_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        DatabaseConnection databaseConnection = new DatabaseConnection();
        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            UUID newId = UUID.randomUUID();
            stmt.setString(1, newId.toString());
            stmt.setString(2, reading.getMeterId());
            stmt.setString(3, reading.getKindOfMeter() != null ? reading.getKindOfMeter().name() : null);
            stmt.setDouble(4, reading.getMeterCount());
            stmt.setDate(5, Date.valueOf(reading.getDateOfReading()));
            stmt.setBoolean(6, reading.getSubstitute());
            stmt.setString(7, reading.getComment());
            stmt.setString(8, reading.getCustomerID());
            stmt.executeUpdate();
            return this.findById(newId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public IReading findById(UUID id) throws RuntimeException {
        String sql = "SELECT id, meter_id, kind_of_meter, meter_count, date_of_reading, substitute, comment, customer_id FROM reading WHERE id = ?";
        DatabaseConnection databaseConnection = new DatabaseConnection();
        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, id.toString());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                IReading reading = new Reading();
                reading.setId(UUID.fromString(rs.getString("id")));
                reading.setMeterId(rs.getString("meter_id"));
                reading.setKindOfMeter(IReading.KindOfMeter.valueOf(rs.getString("kind_of_meter")));
                reading.setMeterCount(rs.getDouble("meter_count"));
                reading.setDateOfReading(rs.getDate("date_of_reading").toLocalDate());
                reading.setSubstitute(rs.getBoolean("substitute"));
                reading.setComment(rs.getString("comment"));
                String customerIdStr = rs.getString("customer_id");
                if (customerIdStr != null) {
                    ICustomer customer = new DAOCustomer().findById(UUID.fromString(customerIdStr));
                    reading.setCustomer(customer);
                }
                return reading;
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateReading(IReading reading) throws RuntimeException {
        String sql = "UPDATE reading SET meter_id = ?, kind_of_meter = ?, meter_count = ?, date_of_reading = ?, substitute = ?, comment = ?, customer_id = ? WHERE id = ?";
        DatabaseConnection databaseConnection = new DatabaseConnection();
        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, reading.getMeterId());
            stmt.setString(2, reading.getKindOfMeter() != null ? reading.getKindOfMeter().name() : null);
            stmt.setString(3, reading.getMeterCount().toString());
            stmt.setDate(4, reading.getDateOfReading() != null ? Date.valueOf(reading.getDateOfReading()) : null);
            stmt.setBoolean(5, reading.getSubstitute());
            stmt.setString(6, reading.getComment());
            stmt.setString(7, reading.getCustomerID());
            stmt.setString(8, reading.getId().toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(UUID id) throws RuntimeException{
        String sql = "DELETE FROM reading WHERE id = ?";
        DatabaseConnection databaseConnection = new DatabaseConnection();
        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, id.toString());
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
