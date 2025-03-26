package dev.hv.model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

public interface IReading extends IId {

   enum KindOfMeter {
      HEIZUNG,
      STROM,
      UNBEKANNT,
      WASSER;
   }

   // Getter-Methoden
   String getComment() throws SQLException;
   ICustomer getCustomer() throws SQLException;
   LocalDate getDateOfReading() throws SQLException;
   KindOfMeter getKindOfMeter() throws SQLException;
   Double getMeterCount() throws SQLException;
   String getMeterId() throws SQLException;
   Boolean getSubstitute() throws SQLException;

   // Setter-Methoden
   void setComment(String comment) throws SQLException;
   void setCustomer(ICustomer customer) throws SQLException;
   void setDateOfReading(LocalDate dateOfReading) throws SQLException;
   void setKindOfMeter(KindOfMeter kindOfMeter) throws SQLException;
   void setMeterCount(Double meterCount) throws SQLException;
   void setMeterId(String meterId) throws SQLException;
   void setSubstitute(Boolean substitute) throws SQLException;

   // Hilfsmethoden
   String printDateOfReading();
}
