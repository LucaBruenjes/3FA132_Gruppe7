import dev.hv.model.Reading;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CSVReaderTest {

    private static final String CSV_CONTENT = """
            Kunde;ec617965-88b4-4721-8158-ee36c38e4db3
            Zählernummer;Xr-2018-2312456ab
            2024-03-20;150.5;Ablesung
            2024-03-21;200.0;Zählertausch
            01.08.2018;7,558;
            """;

    @Test
    void testParseReading() {

        Reading reading = CSVReader.parseReading();


    }

    void testParseReadingCount() {
        
        String sql = "SELECT COUNT(*) FROM hausverwaltung_db WHERE id = ?";
        Connection conn;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setInt(1, id);
                    try (ResultSet rs = pstmt.executeQuery()) {
                        if (rs.next()) {
                            return rs.getInt(1);  // Anzahl der Zeilen zurückgeben
                        }
                    }
                }
            }
    }

