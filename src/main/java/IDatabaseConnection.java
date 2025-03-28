import dev.hv.model.ICustomer;
import org.mariadb.jdbc.Connection;

import java.sql.SQLException;
import java.time.LocalDate;

public interface IDatabaseConnection {

    //IDatabaseConnection getConnection() throws SQLException;
    void createAllTables() throws SQLException;
    void truncateAllTables() throws SQLException;
    void removeAllTables() throws SQLException;
    void closeConnection() throws SQLException;
}
