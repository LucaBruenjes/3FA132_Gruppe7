/*package dev.hv.csvparsing;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ExcelToDatabase {

    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/hausverwaltung_db";
        String username = "julian_testing";
        String password = "julian_passwort";

        String excelFilePath = "src/main/resources";

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
            connection.setAutoCommit(false);

            String sql =
                    "INSERT INTO hausverwaltung_db (Kunde, Zählernummer, Datum, Zählerstand) VALUES (Kunde, Zählernummer, Datum, Zählerstand)";
            PreparedStatement statement = connection.prepareStatement(sql);

            FileInputStream inputStream = new FileInputStream(excelFilePath);
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet firstSheet = workbook.getSheetAt(0);

            for (Row row : firstSheet) {
                if (row.getRowNum() == 0) {
                    continue; // Überspringt die Kopfzeile
                }

                String spalte1 = row.getCell(0).getStringCellValue();
                String spalte2 = row.getCell(1).getStringCellValue();
                String spalte3 = row.getCell(2).getStringCellValue();

                statement.setString(1, spalte1);
                statement.setString(2, spalte2);
                statement.setString(3, spalte3);
                statement.addBatch();
            }

            statement.executeBatch();
            connection.commit();
            workbook.close();
            System.out.println("Excel-Daten erfolgreich importiert.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}*/
