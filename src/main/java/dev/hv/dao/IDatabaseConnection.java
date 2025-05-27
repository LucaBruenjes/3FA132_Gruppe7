package dev.hv.dao;

import java.sql.SQLException;

public interface IDatabaseConnection {

    //dev.hv.dao.IDatabaseConnection getConnection() throws SQLException;
    void createAllTables() throws SQLException;

    void truncateAllTables() throws SQLException;

    void removeAllTables() throws SQLException;

    void closeConnection() throws SQLException;
}
