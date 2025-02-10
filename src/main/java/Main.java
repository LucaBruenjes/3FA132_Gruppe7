import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("config.config.properties")) {
            properties.load(input);
            DatabaseConnection.createAllTables();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

