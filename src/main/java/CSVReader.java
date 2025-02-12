import dev.hv.model.Customer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CSVReader {

    public static List<Reading> parseReading(String csvPath) {
        List<Reading> result = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
            String line = br.readLine();
            Customer customer;
            String customerId;
            String meterId;

            while (line != null) {

                String[] values = line.split(";");
                if(values[0].isEmpty() || values[0].equals("Datum")) {
                    continue;
                }
                if(values[0].equals("Kunde")) {
                    customer = new Customer(UUID.fromString(values[1]));
                    continue;
                }
                if(values[0].equals("Zählernummer")) {
                    meterId = values[1];
                    continue;
                }
                result.add(new Reading(new Customer())






                line = br.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }
