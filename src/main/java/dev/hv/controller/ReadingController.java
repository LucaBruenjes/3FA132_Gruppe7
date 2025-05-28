package dev.hv.controller;

import dev.hv.dao.DAOCustomer;
import dev.hv.dao.DAOReading;
import dev.hv.model.Customer;
import dev.hv.model.ICustomer;
import dev.hv.model.Reading;
import dev.hv.model.IReading;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.Objects;
import java.util.UUID;

@Path("/")
public class ReadingController {
    @POST
    @Path("readings")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addReading(Reading reading) {
        if (reading.getCustomerID() == null || Objects.equals(reading.getCustomerID(), "")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("CustomerID muss befüllt sein").build();
        }
        try {
            ICustomer customer = new DAOCustomer().findById(UUID.fromString(reading.getCustomerID()));
            if (customer == null) {
                Customer customerToCreate = new Customer();
                customerToCreate.setId(UUID.fromString(reading.getCustomerID()));
                customerToCreate.setGender(ICustomer.Gender.U);
                new DAOCustomer().createCustomer(customerToCreate);
            }
            IReading createdReading = new DAOReading().createReading(reading);
            return Response.status(Response.Status.CREATED).entity(createdReading).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("readings")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateReading(Reading updateReading) throws SQLException {
        IReading reading = new Reading().getReadingById(updateReading.getId());
        if (reading == null) {
            String message = "Diese Ablesung exestiert nicht";
            return Response.status(Response.Status.NOT_FOUND).entity(message).build();
        }
        try {
            new Reading().updateReading(updateReading);
            String message = "Die Ablesung wurde bearbeitet";
            return Response.status(Response.Status.OK).entity(message).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("readings/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReadingById(@PathParam("uuid") UUID uuid) {
        try {
            IReading reading = new DAOReading().findById(uuid);
            if (reading == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Reading existiert nicht").build();
            }
            return Response.status(Response.Status.OK).entity(reading).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("readings/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteReadingById(@PathParam("uuid") UUID uuid) {
        try {
            IReading reading = new DAOReading().findById(uuid);
            if (reading == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Reading existiert nicht").build();
            }
            new DAOReading().deleteById(uuid);
            return Response.status(Response.Status.OK).entity(reading).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
