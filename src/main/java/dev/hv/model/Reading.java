package dev.hv.model;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Reading implements IReading {

    private final Connection connection;
    private UUID readingId;
    private String meterId;
    private KindOfMeter kindOfMeter;
    private Double meterCount;
    private LocalDate dateOfReading;
    private Boolean substitute;
    private String comment;
    private ICustomer customer;

    // Konstruktor, um eine Instanz mit einer ID zu initialisieren (normalerweise beim Abrufen aus der DB)
    public Reading(UUID id) throws SQLException {
        this.readingId = id;
        this.connection = DriverManager.getConnection(
                System.getProperty("url.name"),
                System.getProperty("user.name"),
                System.getProperty("pw.name"));
        // Hier würden wir die anderen Felder aus der DB laden, falls notwendig.
    }

    @Override
    public String getComment() throws SQLException {
        String query = "SELECT comment FROM reading WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setObject(1, readingId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("comment");
                }
            }
        }
        return null;
    }

    @Override
    public ICustomer getCustomer() throws SQLException {
        // Annehmen, dass ein Customer mit der ID aus der reading-Tabelle verknüpft ist
        String query = "SELECT customer_id FROM reading WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setObject(1, readingId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    UUID customerId = UUID.fromString(rs.getString("customer_id"));
                    // Wir gehen davon aus, dass der Customer mit dieser ID geladen werden kann
                    this.customer = new Customer(customerId); // Wir müssen den Customer-Constructor ggf. anpassen
                    return customer;
                }
            }
        }
        return null;
    }

    @Override
    public LocalDate getDateOfReading() throws SQLException {
        String query = "SELECT date_of_reading FROM reading WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setObject(1, readingId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDate("date_of_reading").toLocalDate();
                }
            }
        }
        throw new SQLException("No reading found for id: " + readingId);
    }

    @Override
    public KindOfMeter getKindOfMeter() throws SQLException {
        String query = "SELECT kind_of_meter FROM reading WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setObject(1, readingId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return KindOfMeter.valueOf(rs.getString("kind_of_meter"));
                }
            }
        }
        throw new SQLException("No reading found for id: " + readingId);
    }

    @Override
    public Double getMeterCount() throws SQLException {
        String query = "SELECT meter_count FROM reading WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setObject(1, readingId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("meter_count");
                }
            }
        }
        return null;
    }

    @Override
    public String getMeterId() throws SQLException {
        String query = "SELECT meter_id FROM reading WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setObject(1, readingId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("meter_id");
                }
            }
        }
        return null;
    }

    @Override
    public Boolean getSubstitute() throws SQLException {
        String query = "SELECT substitute FROM reading WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setObject(1, readingId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getBoolean("substitute");
                }
            }
        }
        return null;
    }

    @Override
    public void setComment(String comment) throws SQLException {
        this.comment = comment;
        String query = "UPDATE reading SET comment = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, comment);
            ps.setObject(2, readingId);
            ps.executeUpdate();
        }
    }

    @Override
    public void setCustomer(ICustomer customer) throws SQLException {
        this.customer = customer;
        String query = "UPDATE reading SET customer_id = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setObject(1, customer.getId());
            ps.setObject(2, readingId);
            ps.executeUpdate();
        }
    }

    @Override
    public void setDateOfReading(LocalDate dateOfReading) throws SQLException {
        this.dateOfReading = dateOfReading;
        String query = "UPDATE reading SET date_of_reading = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setDate(1, Date.valueOf(dateOfReading));
            ps.setObject(2, readingId);
            ps.executeUpdate();
        }
    }

    @Override
    public void setKindOfMeter(KindOfMeter kindOfMeter) throws SQLException {
        this.kindOfMeter = kindOfMeter;
        String query = "UPDATE reading SET kind_of_meter = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, kindOfMeter.name());
            ps.setObject(2, readingId);
            ps.executeUpdate();
        }
    }

    @Override
    public void setMeterCount(Double meterCount) throws SQLException {
        this.meterCount = meterCount;
        String query = "UPDATE reading SET meter_count = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setDouble(1, meterCount);
            ps.setObject(2, readingId);
            ps.executeUpdate();
        }
    }

    @Override
    public void setMeterId(String meterId) throws SQLException {
        this.meterId = meterId;
        String query = "UPDATE reading SET meter_id = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, meterId);
            ps.setObject(2, readingId);
            ps.executeUpdate();
        }
    }

    @Override
    public void setSubstitute(Boolean substitute) throws SQLException {
        this.substitute = substitute;
        String query = "UPDATE reading SET substitute = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setBoolean(1, substitute);
            ps.setObject(2, readingId);
            ps.executeUpdate();
        }
    }

    @Override
    public String printDateOfReading() {
        return dateOfReading != null ? dateOfReading.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "Unknown Date";
    }

    @Override
    public UUID getId() {
        return readingId;
    }

    @Override
    public void setId(UUID id) throws SQLException {
        String query = "UPDATE reading SET id = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setObject(1, id);
            ps.setObject(2, readingId);
            ps.executeUpdate();
            this.readingId = id;
        }
    }

    // Close the connection
    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
