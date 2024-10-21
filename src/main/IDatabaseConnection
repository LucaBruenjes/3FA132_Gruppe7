import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection implements IDatabaseConnection {
    private Connection connection;

    @Override
    public IDatabaseConnection openConnection(Properties properties) throws SQLException {
        String url = properties.getProperty("user.db.url");
        String user = properties.getProperty("user.db.user");
        String password = properties.getProperty("user.db.pw");
        connection = DriverManager.getConnection(url, user, password);
        return this;
    }

    @Override
    public void createAllTables() throws SQLException {
        String createCustomerTable = "CREATE TABLE IF NOT EXISTS Customer (id UUID PRIMARY KEY, first_name VARCHAR(100), last_name VARCHAR(100), birth_date DATE, gender ENUM('D', 'M', 'U', 'W'))";
        String createReadingTable = "CREATE TABLE IF NOT EXISTS Reading (id UUID PRIMARY KEY, meter_id VARCHAR(50), kind_of_meter ENUM('HEIZUNG', 'STROM', 'WASSER', 'UNBEKANNT'), meter_count DOUBLE, date_of_reading DATE, substitute BOOLEAN, comment TEXT, customer_id UUID, FOREIGN KEY (customer_id) REFERENCES Customer(id) ON DELETE SET NULL)";

        connection.createStatement().execute(createCustomerTable);
        connection.createStatement().execute(createReadingTable);
    }

    @Override
    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    // Implement other methods (truncateAllTables, removeAllTables) as needed
}
