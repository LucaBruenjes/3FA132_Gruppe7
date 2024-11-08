import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection implements IDatabaseConnection {
    private Connection connection;



    static Connection OpenConnection() throws SQLException {
        return DriverManager.getConnection(
                "localhost:3306/hausverwaltung_db, julian_testing, julian_passwort"
        );
    }
    @Override
    /*public DatabaseConnection openConnection(Properties properties) throws SQLException {

        String localUsername = System.getProperty("user.name").toLowerCase();

        // Use the correct property keys matching the database.properties file
        String url = properties.getProperty("user.db.url");
        String user = properties.getProperty("user.db.user");
        String password = properties.getProperty("user.db.pw");

        // Establishing a connection using the properties
        connection = DriverManager.getConnection(url, user, password);
        return this;
    }*/

    @Override
    public void createAllTables() throws SQLException {
        // Use VARCHAR(36) for UUIDs
        String createCustomerTable = "CREATE TABLE IF NOT EXISTS Customer (" +
                "id VARCHAR(36) PRIMARY KEY, " +
                "first_name VARCHAR(100), " +
                "last_name VARCHAR(100), " +
                "birth_date DATE, " +
                "gender ENUM('D', 'M', 'U', 'W')" +
                ")";

        String createReadingTable = "CREATE TABLE IF NOT EXISTS Reading (" +
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
        connection.createStatement().execute(createCustomerTable);
        connection.createStatement().execute(createReadingTable);
    }

    @Override
    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public Object prepareStmt(String sql) {

    }

    // Implement other methods (truncateAllTables, removeAllTables) as needed
}


