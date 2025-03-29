package dev.hv.model;

import java.sql.SQLException;
import java.time.LocalDate;

public interface ICustomer extends IId {

    enum Gender {
        D, // divers
        M, // männlich
        U, // unbekannt
        W, // weiblich
    }

    LocalDate getBirthDate() throws SQLException;

    String getFirstName() throws SQLException;

    Gender getGender() throws SQLException;

    String getLastName() throws SQLException;

    void setBirthDate(LocalDate birtDate) throws SQLException;

    void setFirstName(String firstName) throws SQLException;

    void setGender(Gender gender) throws SQLException;

    void setLastName(String lastName) throws SQLException;
}
