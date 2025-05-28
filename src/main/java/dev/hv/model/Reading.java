package dev.hv.model;

import dev.hv.dao.DAOCustomer;
import dev.hv.dao.DAOReading;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

public class Reading implements IReading {

    private UUID id;
    private String comment;
    private String customerID;
    private ICustomer customer;
    private LocalDate dateOfReading;
    private IReading.KindOfMeter kindOfMeter;
    private double meterCount;
    private String meterId;
    private boolean substitute;

    public Reading(UUID id, String comment, String customerID, LocalDate dateOfReading, IReading.KindOfMeter kindOfMeter, double meterCount, String meterId, boolean substitute) {
        this.id = id;
        this.comment = comment;
        this.customerID = customerID;
        this.dateOfReading = dateOfReading;
        this.kindOfMeter = kindOfMeter;
        this.meterCount = meterCount;
        this.meterId = meterId;
        this.substitute = substitute;
    }

    public Reading() {}

    @Override
    public ICustomer getCustomer() {
        return customer;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public void setCustomer(ICustomer customer) {
        this.customer = customer;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public LocalDate getDateOfReading() {
        return dateOfReading;
    }

    public void setDateOfReading(LocalDate dateOfReading) {
        this.dateOfReading = dateOfReading;
    }

    public Double getMeterCount() {
        return meterCount;
    }

    public void setMeterCount(double meterCount) {
        this.meterCount = meterCount;
    }

    public IReading.KindOfMeter getKindOfMeter() {
        return kindOfMeter;
    }

    public void setKindOfMeter(IReading.KindOfMeter kindOfMeter) {
        this.kindOfMeter = kindOfMeter;
    }

    @Override
    public void setMeterCount(Double meterCount) {

    }

    public String getMeterId() {
        return meterId;
    }

    public void setMeterId(String meterId) {
        this.meterId = meterId;
    }

    @Override
    public void setSubstitute(Boolean substitute) {

    }

    public Boolean getSubstitute() {

        return substitute;
    }

    @Override
    public String printDateOfReading() {
        return "";
    }

    public void setSubstitute(boolean substitute) {

        this.substitute = substitute;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    public IReading getReadingById(UUID id) {
        DAOReading dao = new DAOReading();
        return dao.findById(id);
    }

    public void updateReading(IReading reading) {
        DAOReading dao = new DAOReading();
        dao.updateReading(reading);
    }
}
