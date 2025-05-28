package dev.hv.model;

import java.sql.SQLException;
import java.util.UUID;

public interface IId {

    UUID getId() throws SQLException;

    void setId(UUID id) throws SQLException;
}
