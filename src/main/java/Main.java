import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {

        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            databaseConnection.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

