import dev.hv.model.ICustomer;
import dev.hv.model.IReading;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class CSVReaderTest {

    static void OpenConnection() throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();) {

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    void testReadFromCSVReader() {

        String comment = "";
        ICustomer customer = "ec617965-88b4-4721-8158-ee36c38e4db3";
        LocalDate dateOfReading = 01.05.2018;
        IReading.KindOfMeter kindOfMeter = Heizung;
        double meterCount = 6.859;
        String meterId = "Xr-2018-2312456ab";
        boolean substitute = false;


    }
}
