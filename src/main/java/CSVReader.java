import dev.hv.model.Customer;
import dev.hv.model.IReading;
import dev.hv.model.Reading;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CSVReader {

    public static List<Reading> parseReading(String csvPath, IReading.KindOfMeter kindOfMeter) {
        List<Reading> result = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
            String line = br.readLine();
            Customer customer = null;
            String customerId;
            String meterId = "";
            boolean substitute = false;

            while (line != null) {

                String[] values = line.split(";");
                if (values[0].isEmpty() || values[0].equals("Datum")) {
                    continue;
                }
                if (values[0].equals("Kunde")) {
                    customer = new Customer(UUID.fromString(values[1]));
                    continue;
                }
                if (values[0].equals("Zählernummer")) {
                    meterId = values[1];
                    continue;
                }
                if (values[2].contains("Zählertausch")) {
                    substitute = true;
                }
                String dateStr = values[0].replace("\"", "").trim();
                result.add(new Reading(values[2], customer, LocalDate.parse(dateStr), kindOfMeter, Double.parseDouble(values[1].replace(", ", ". ")), meterId, substitute));
                line = br.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
