import dev.hv.model.IReading;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class DAOReading {

    public IReading createReading(IReading reading) {
        String sql = "INSERT INTO reading (id, meter_id, kind_of_meter, meter_count, date_of_reading, substitute, comment, customer_id, FOREIGN KEY) VALUES (?, ?, ?, ?, ?, ?, ?)";
        DatabaseConnection databaseConnection = new DatabaseConnection();
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            UUID newId = (reading.getId() == null) ? UUID.randomUUID() : reading.getId();
            stmt.setString(1, newId.toString());
            stmt.setString(2, reading.getMeterId());
            stmt.setString(3, reading.getKindOfMeter() != null ? reading.getKindOfMeter().name() : null);
            stmt.setDouble(4, reading.getMeterCount());
            stmt.setString(5, reading.printDateOfReading());
            stmt.setBoolean(6, reading.getSubstitute());
            stmt.setString(7, reading.getComment());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
