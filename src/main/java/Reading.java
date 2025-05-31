/*import dev.hv.model.Customer;
import dev.hv.model.ICustomer;
import dev.hv.model.IReading;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Reading implements IReading {
    Statement stmt;
    UUID readingId;

    public Reading(UUID id) throws SQLException {
        readingId = id;

        Connection con = DriverManager.getConnection(
                System.getProperty("url.name"),
                System.getProperty("user.name"),
                System.getProperty("pw.name"));
        stmt = con.createStatement();
    }

    @Override
    public String getComment() throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT comment FROM Reading WHERE id = " + readingId + ")");
        return rs.getString("comment");
    }

    @Override
    public ICustomer getCustomer() throws SQLException {
        String query = "SELECT customer FROM Reading WHERE id = " + readingId;
        ResultSet rs = stmt.executeQuery(query);

        if (rs.next()) {
            UUID id = UUID.randomUUID();
            String name = rs.getString("name");
            String lastName = rs.getString("last_name");
            LocalDate birthDate = rs.getDate("birth_date").toLocalDate();
            ICustomer.Gender gender = ICustomer.Gender.valueOf(rs.getString("gender"));

            return new Customer(id, name, lastName, birthDate, gender);
        } else {
            return null;
        }
    }

    @Override
    public LocalDate getDateOfReading() throws SQLException {
        String query = "SELECT date_of_reading FROM Reading WHERE id = " + readingId;
        ResultSet rs = stmt.executeQuery(query);
        if (rs.next()) {
            return rs.getDate("date_of_reading").toLocalDate();
        }

        throw new SQLException("No reading found for id: " + readingId);
    }

    @Override
    public KindOfMeter getKindOfMeter() throws SQLException {
        String query = "SELECT kind_of_meter FROM Reading WHERE id = " + readingId;
        ResultSet rs = stmt.executeQuery(query);
        if (rs.next()) {
            String kindOfMeterValue = rs.getString("kind_of_meter");
            return KindOfMeter.valueOf(kindOfMeterValue);
        }

        throw new SQLException("No reading found for id: " + readingId);
    }

    @Override
    public Double getMeterCount() throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT meter_count FROM Reading WHERE id = " + readingId + ")");
        return rs.getDouble("meter_count");
    }

    @Override
    public String getMeterId() throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT meter_id FROM Reading WHERE id = " + readingId + ")");

        return rs.getString("meter_id");
    }

    @Override
    public Boolean getSubstitute() throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT substitute FROM Reading WHERE id = " + readingId + ")");
        return rs.getBoolean("substitute");
    }

    @Override
    public String printDateOfReading() {

        return "";
    }

    @Override
    public void setComment(String comment) throws SQLException {
        stmt.executeQuery("INSERT INTO Reading (comment) VALUES (" + comment + ") WHERE reading_id = " + readingId + ")");
    }

    @Override
    public void setCustomer(ICustomer customer) throws SQLException {
        stmt.executeQuery("INSERT INTO Reading (customer_id) VALUES (" + customer.getId() + ") WHERE reading_id = " + readingId + ")");
    }

    @Override
    public void setDateOfReading(LocalDate dateOfReading) throws SQLException {
        stmt.executeQuery("INSERT INTO Reading (date_of_reading) VALUES (" + dateOfReading.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ") WHERE reading_id = " + readingId + ")");
    }

    @Override
    public void setKindOfMeter(KindOfMeter kindOfMeter) throws SQLException {
        stmt.executeQuery("INSERT INTO Reading (kind_of_meter) VALUES (" + kindOfMeter + ") WHERE reading_id = " + readingId + ")");
    }

    @Override
    public void setMeterCount(Double meterCount) throws SQLException {
        stmt.executeQuery("INSERT INTO Reading (meter_count) VALUES (" + meterCount + ") WHERE reading_id = " + readingId + ")");
    }

    @Override
    public void setMeterId(String meterId) throws SQLException {
        stmt.executeQuery("INSERT INTO Reading (meter_id) VALUES (" + meterId + ") WHERE reading_id = " + readingId + ")");
    }

    @Override
    public void setSubstitute(Boolean substitute) throws SQLException {
        stmt.executeQuery("INSERT INTO Reading (substitute) VALUES (" + substitute + ") WHERE reading_id = " + readingId + ")");
    }

    @Override
    public UUID getId() {
        return readingId;
    }

    @Override
    public void setId(UUID id) throws SQLException {
        stmt.executeQuery("INSERT INTO Reading (id) VALUES (" + readingId + ")");
        readingId = id;
    }
}*/
