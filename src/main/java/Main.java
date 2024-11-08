import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("config.properties.config.test")) {
            properties.load(input);
            DatabaseConnection dbConnection = new DatabaseConnection();
            DatabaseConnection.OpenConnection();
            dbConnection.createAllTables();
            dbConnection.closeConnection();
            System.out.println("Datenbanktabellen erfolgreich erstellt.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

