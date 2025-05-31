package dev.hv.model;

import dev.hv.dao.DAOCustomer;
import java.time.LocalDate;
import java.util.UUID;

public class Customer implements ICustomer {
    private UUID id;
    private LocalDate birthDate;
    private String firstName;
    private ICustomer.Gender gender;
    private String lastName;

    public Customer(UUID id, String firstName, String lastName, LocalDate birthDate, ICustomer.Gender gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public Customer() {

    }

    public Customer(UUID id) {
        this.id = id;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public String getFirstName() {
        return firstName;
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
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setGender(ICustomer.Gender gender) {

        this.gender = gender;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    public void createCustomer() throws RuntimeException {
        DAOCustomer dao = new DAOCustomer();
        dao.createCustomer(this);
    }

    public ICustomer getCustomerById(UUID id) {
        DAOCustomer dao = new DAOCustomer();
        return dao.findById(id);
    }

    public void updateCustomer(ICustomer customer) {
        DAOCustomer dao = new DAOCustomer();
        dao.updateCustomer(customer);
    }
}

