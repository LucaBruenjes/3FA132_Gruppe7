public class Main {
    public static void main(String[] args) {

        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            databaseConnection.createAllTables();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

