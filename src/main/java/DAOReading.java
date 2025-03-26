import dev.hv.model.ICustomer;
import dev.hv.model.IReading;
import dev.hv.model.Reading;
import dev.hv.model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class DAOReading {

    // Die Methode erstellt eine neue Ablesung in der Datenbank und gibt sie zurück
    public IReading createReading(IReading reading) {
        String sql = "INSERT INTO reading (id, meter_id, kind_of_meter, meter_count, date_of_reading, substitute, comment, customer_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        // Verbindungsmanagement und Sicherstellen einer Transaktion
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Generiere eine neue ID, wenn sie noch nicht gesetzt ist
            UUID newId = (reading.getId() == null) ? UUID.randomUUID() : reading.getId();
            stmt.setString(1, newId.toString());
            stmt.setString(2, reading.getMeterId());
            stmt.setString(3, reading.getKindOfMeter() != null ? reading.getKindOfMeter().name() : null);
            stmt.setDouble(4, reading.getMeterCount());
            stmt.setDate(5, java.sql.Date.valueOf(reading.getDateOfReading()));  // Annahme: getDateOfReading gibt ein LocalDate zurück
            stmt.setBoolean(6, reading.getSubstitute());
            stmt.setString(7, reading.getComment());

            // Annahme: Der Customer-Objekt hat eine getId-Methode
            if (reading.getCustomer() != null) {
                stmt.setString(8, reading.getCustomer().getId().toString());
            } else {
                stmt.setNull(8, java.sql.Types.NULL);  // Falls kein Customer vorhanden ist
            }

            // Führt das Insert aus
            int rowsAffected = stmt.executeUpdate();

            // Wenn die Zeilen betroffen sind, hol dir die generierte ID
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        // Hier könnte man die ID des gerade eingefügten Datensatzes zurückgeben, falls sie gebraucht wird.
                        // Bei UUID ist es unwahrscheinlich, dass wir sie hier benötigen, aber in einer anderen Struktur kann es sinnvoll sein.
                        UUID generatedId = UUID.fromString(generatedKeys.getString(1));
                        // Rückgabe der aktualisierten Reading-Instanz
                        reading.setId(generatedId);  // Setzt die ID des Objekts auf die generierte ID
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Erstellen der Ablesung", e);
        }

        return reading;  // Rückgabe des erstellten Objekts
    }

    // Optional: Die Methode, um eine Ablesung aus der Datenbank zu holen (falls benötigt)
    public IReading getReadingById(UUID readingId) throws SQLException {
        String sql = "SELECT id, meter_id, kind_of_meter, meter_count, date_of_reading, substitute, comment, customer_id FROM reading WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, readingId.toString());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    IReading reading = new Reading(readingId); // Annahme, dass Reading auch eine ID erwartet
                    reading.setMeterId(rs.getString("meter_id"));
                    reading.setKindOfMeter(IReading.KindOfMeter.valueOf(rs.getString("kind_of_meter")));
                    reading.setMeterCount(rs.getDouble("meter_count"));
                    reading.setDateOfReading(rs.getDate("date_of_reading").toLocalDate());
                    reading.setSubstitute(rs.getBoolean("substitute"));
                    reading.setComment(rs.getString("comment"));

                    // Falls der Customer ebenfalls benötigt wird:
                    UUID customerId = UUID.fromString(rs.getString("customer_id"));
                    ICustomer customer = new Customer(customerId); // Die Kunden-ID annehmen
                    reading.setCustomer(customer);

                    return reading;
                }
            }
        }

        return null;  // Kein passendes Reading gefunden
    }
}
