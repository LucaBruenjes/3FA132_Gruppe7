import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        try {
            // Tabellen anlegen
            DatabaseConnection databaseConnection = new DatabaseConnection();
            databaseConnection.createAllTables();

            // HTTP-Server starten auf Port 8080
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

            // HTML-Datei als Root-Seite bereitstellen
            server.createContext("/", new StaticFileHandler("src/main/index.html"));

            // Server starten
            server.setExecutor(null); // default executor
            server.start();

            System.out.println("Server läuft unter http://localhost:8080/");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Hilfsklasse zum Ausliefern der HTML-Seite
    static class StaticFileHandler implements HttpHandler {
        private final String filePath;

        public StaticFileHandler(String filePath) {
            this.filePath = filePath;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if (!exchange.getRequestMethod().equalsIgnoreCase("GET")) {
                exchange.sendResponseHeaders(405, -1); // Methode nicht erlaubt
                return;
            }

            byte[] response = Files.readAllBytes(Paths.get(filePath));
            exchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
            exchange.sendResponseHeaders(200, response.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response);
            }
        }
    }
}
