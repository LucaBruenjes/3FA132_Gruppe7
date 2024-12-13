package dev.hv.model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

public class Customer implements ICustomer
{
    private LocalDate birthDate;
    private String name;
    private ICustomer.Gender gender;
    private String lastName;

    public Customer(String name, String lastName, LocalDate birthDate, ICustomer.Gender gender) {
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public String getFirstName() throws SQLException {
        return "";
    }

    public String getName() {
        return name;
    }

    public ICustomer.Gender getGender() {
        return gender;
    }

    public String getLastName() {
        return lastName;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public void setFirstName(String firstName) throws SQLException {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(ICustomer.Gender gender) {
        this.gender = gender;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public UUID getId() throws SQLException {
        return null;
    }

    @Override
    public void setId(UUID id) throws SQLException {

    }
}

