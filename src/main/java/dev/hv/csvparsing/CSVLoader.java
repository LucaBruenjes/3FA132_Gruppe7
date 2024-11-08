package dev.hv.csvparsing;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CSVLoader {

    public static void main(String[] args) {
        CSVLoader loader = new CSVLoader();
        loader.loadCsvFile("heizung.csv");
        loader.loadCsvFile("kunden_utf8.csv");
        loader.loadCsvFile("strom.csv");
        loader.loadCsvFile("wasser.csv");
    }

    public void loadCsvFile(String fileName) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) {
                System.out.println("Datei " + fileName + " nicht gefunden.");
                return;
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                String[] data;
                while ((line = reader.readLine()) != null) {
                    String[] data = new i++[line]
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
