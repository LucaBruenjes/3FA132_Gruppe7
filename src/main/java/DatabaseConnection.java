import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseConnection {

    private static Properties properties;

    static {
        try {
            properties = new Properties();
            try (InputStream input = DatabaseConnection.class.getClassLoader().getResourceAsStream("config.properties"))
            {
                if (input == null) {
                    throw new RuntimeException("Datenbank Objekte in diesem Pfad nicht gefunden");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Datenbank laden fehlgeschlagen", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = properties.getProperty("db.url");
        String name = properties.getProperty("db.user");
        String pw = properties.getProperty("db.pw");
       return DriverManager.getConnection(url, name, pw);
    }

    public static void createAllTables() {
        // Use VARCHAR(36) for UUIDs
        String createCustomersTable = "CREATE TABLE IF NOT EXISTS customers (" +
                "id VARCHAR(36) PRIMARY KEY, " +
                "first_name VARCHAR(100), " +
                "last_name VARCHAR(100), " +
                "birth_date DATE, " +
                "gender ENUM('D', 'M', 'U', 'W')" +
                ")";

        String createReadingTable = "CREATE TABLE IF NOT EXISTS reading (" +
                "id VARCHAR(36) PRIMARY KEY, " +
                "meter_id VARCHAR(50), " +
                "kind_of_meter ENUM('HEIZUNG', 'STROM', 'WASSER', 'UNBEKANNT'), " +
                "meter_count DOUBLE, " +
                "date_of_reading DATE, " +
                "substitute BOOLEAN, " +
                "comment TEXT, " +
                "customer_id VARCHAR(36), " +
                "FOREIGN KEY (customer_id) REFERENCES Customer(id) ON DELETE SET NULL" +
                ")";

        // Execute the SQL commands to create the tables
        try (Connection connection = getConnection()) {
            try (Statement stmt = connection.createStatement()) {
                stmt.execute(createCustomersTable);
                stmt.execute(createReadingTable);
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
                throw new RuntimeException("Tabellen erstellen fehlgeschlagen");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Datenbankverbindung fehlgeschlagen beim Erstellen von Tabellen");
        }
    }

    // Implement other methods (truncateAllTables, removeAllTables) as needed
}


