package dev.hv.model;
import dev.hv.model.ICustomer;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

public class Reading implements IReading{

    private String comment;
    private ICustomer customer;
    private LocalDate dateOfReading;
    private IReading.KindOfMeter kindOfMeter;
    private double meterCount;
    private String meterId;
    private boolean substitute;

    public Reading(String comment, ICustomer customer, LocalDate dateOfReading, IReading.KindOfMeter kindOfMeter, double meterCount, String meterId, boolean substitute) {
        this.comment = comment;
        this.customer = customer;
        this.dateOfReading = dateOfReading;
        this.kindOfMeter = kindOfMeter;
        this.meterCount = meterCount;
        this.meterId = meterId;
        this.substitute = substitute;
    }

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
    public void setCustomer(ICustomer customer) throws SQLException {

    }

    public LocalDate getDateOfReading() {
        return dateOfReading;
    }

    public void setDateOfReading(LocalDate dateOfReading) {
        this.dateOfReading = dateOfReading;
    }

    public double getMeterCount() {
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
    public void setMeterCount(Double meterCount) throws SQLException {

    }

    public String getMeterId() {
        return meterId;
    }

    public void setMeterId(String meterId) {
        this.meterId = meterId;
    }

    @Override
    public void setSubstitute(Boolean substitute) throws SQLException {

    }

    public boolean getSubstitute() {
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
    public UUID getId() throws SQLException {
        return null;
    }

    @Override
    public void setId(UUID id) throws SQLException {

    }
}
