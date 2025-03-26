import dev.hv.model.ICustomer;
import org.junit.jupiter.api.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DAOCustomerTest {

    private Connection connection;


    static void OpenConnection() throws SQLException {
        DriverManager.getConnection(
                "localhost:3306/hausverwaltung_db, julian_testing, julian_passwort"
        );
    }

    @BeforeEach
    void clearDatabase() throws SQLException {
        // Lösche alle Einträge vor jedem Test
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DELETE FROM Customer");
        }
    }

    @AfterAll
    void closeDatabase() throws SQLException {
        connection.close();
    }

    @Test
    void testInsertAndRetrieveCustomer() throws SQLException {
        // Customer-DAO erstellen
        DAOCustomer customerDao = new DAOCustomer();

        // Testdaten erstellen
        UUID testId = UUID.randomUUID();
        Date birthDate = Date.valueOf("1995-01-15");
        String firstName = "Anna";
        String lastName = "Müller";
        ICustomer.Gender gender = ICustomer.Gender.W;

        // Neuen Customer in die Datenbank einfügen
        try (PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO Customer (id, birth_date, first_name, last_name, gender) VALUES (?, ?, ?, ?, ?)")) {
            stmt.setString(1, testId.toString());
            stmt.setDate(2, birthDate);
            stmt.setString(3, firstName);
            stmt.setString(4, lastName);
            stmt.setString(5, gender.name());
            stmt.executeUpdate();
        }

        // Customer aus der Datenbank abrufen
        DAOCustomer retrievedCustomer = customerDao.getCustomer(testId);

        // Assertions
        assertNotNull(retrievedCustomer);
        assertEquals(birthDate, retrievedCustomer.getCustomer(testId).birthDate);
        assertEquals(firstName, retrievedCustomer.getCustomer(testId).firstName);
        assertEquals(lastName, retrievedCustomer.getCustomer(testId).lastName);
        assertEquals(gender, retrievedCustomer.getCustomer(testId).gender);
    }

}
