import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import dev.hv.controller.CustomerController;
import dev.hv.dao.DatabaseConnection;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Server {

    private static HttpServer server;

    public static void main(final String[] args) {
        try {
            //Datenbanktabellen anlegen
            DatabaseConnection databaseConnection = new DatabaseConnection();
            databaseConnection.createAllTables();
            System.out.println("Tabellen erfolgreich erstellt");

            //Jersey-Konfiguration für REST-Endpunkte
            final ResourceConfig rc = new ResourceConfig()
                    .packages("dev.hv.controller")
                    .register(CustomerController.class); // weitere Controller hier registrieren

            //Server vorbereiten auf Port 8080
            URI baseUri = URI.create("http://localhost:8080/");
            server = JdkHttpServerFactory.createHttpServer(baseUri, rc, false); // false = noch nicht starten

            //HTML-Datei auf Root-Pfad ("/ui") ausliefern
            server.createContext("/ui", new StaticFileHandler("src/main/index.html"));

            //Server starten
            server.start();
            System.out.println("Server läuft unter http://localhost:8080/");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Handler zum Ausliefern der HTML-Datei
    static class StaticFileHandler implements HttpHandler {
        private final String filePath;

        public StaticFileHandler(String filePath) {
            this.filePath = filePath;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            System.out.println("Anfrage empfangen: " + exchange.getRequestURI());

            if (!exchange.getRequestMethod().equalsIgnoreCase("GET")) {
                exchange.sendResponseHeaders(405, -1); // Methode nicht erlaubt
                return;
            }

            var path = Paths.get(filePath);
            if (!Files.exists(path)) {
                String msg = "<h1>Datei nicht gefunden: " + path.toAbsolutePath() + "</h1>";
                exchange.sendResponseHeaders(404, msg.length());
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(msg.getBytes());
                }
                return;
            }

            byte[] response = Files.readAllBytes(path);
            exchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
            exchange.sendResponseHeaders(200, response.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response);
            }

            System.out.println("index.html ausgeliefert.");
        }
    }
}
