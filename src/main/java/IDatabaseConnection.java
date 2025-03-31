import java.sql.SQLException;

public interface IDatabaseConnection {

    //IDatabaseConnection getConnection() throws SQLException;
    void createAllTables() throws SQLException;

    void truncateAllTables() throws SQLException;

    void removeAllTables() throws SQLException;

    void closeConnection() throws SQLException;
}
