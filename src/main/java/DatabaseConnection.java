import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseConnection implements IDatabaseConnection {

    private static Properties properties;
    private Connection con;

    public DatabaseConnection() {
        String rootPath = Paths.get(".").normalize().toAbsolutePath().toString();
        String appConfigPath = rootPath + "\\src\\main\\resources\\config.properties";
        properties = new Properties();

        try {
            properties.load(new FileInputStream(appConfigPath));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        if (con == null) {
            String url = properties.getProperty("db.url");
            String name = properties.getProperty("db.user");
            String pw = properties.getProperty("db.pw");
            con = DriverManager.getConnection(url, name, pw);
        }

        return con;
    }

    public void createAllTables() {
        // Use VARCHAR(36) for UUIDs
        String createCustomersTable = "CREATE TABLE IF NOT EXISTS customers (" +
                "id UUID PRIMARY KEY, " +
                "first_name VARCHAR(100), " +
                "last_name VARCHAR(100), " +
                "birth_date DATE, " +
                "gender ENUM('D', 'M', 'U', 'W')" +
                ")";

        String createReadingTable = "CREATE TABLE IF NOT EXISTS reading (" +
                "id UUID PRIMARY KEY, " +
                "meter_id VARCHAR(50), " +
                "kind_of_meter ENUM('HEIZUNG', 'STROM', 'WASSER', 'UNBEKANNT'), " +
                "meter_count DOUBLE, " +
                "date_of_reading DATE, " +
                "substitute BOOLEAN, " +
                "comment TEXT, " +
                "customer_id UUID, " +
                "FOREIGN KEY (customer_id) REFERENCES Customers (id) ON DELETE SET NULL" +
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

    @Override
    public void truncateAllTables() throws SQLException {
        String truncateCustomersTable = "TRUNCATE FROM customers";
        String truncateReadingTable = "TRUNCATE FROM reading";

        // Execute the SQL commands to delete the tables
        try (Connection connection = getConnection()) {
            try (Statement stmt = connection.createStatement()) {
                stmt.execute(truncateCustomersTable);
                stmt.execute(truncateReadingTable);
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
                throw new RuntimeException("Tabelleninhalt löschen fehlgeschlagen");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Datenbankverbindung fehlgeschlagen beim Tabelleninhalt Löschen von Tabellen");
        }
    }

    @Override
    public void removeAllTables() throws SQLException {
        String deleteCustomersTable = "DELETE FROM customers";
        String deleteReadingTable = "DELETE FROM reading";

        // Execute the SQL commands to delete the tables
        try (Connection connection = getConnection()) {
            try (Statement stmt = connection.createStatement()) {
                stmt.execute(deleteCustomersTable);
                stmt.execute(deleteReadingTable);
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
                throw new RuntimeException("Tabellen löschen fehlgeschlagen");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Datenbankverbindung fehlgeschlagen beim Löschen von Tabellen");
        }
    }

    @Override
    public void closeConnection() throws SQLException {
        con.close();
    }
}


