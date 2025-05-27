package dev.hv.controller;

import dev.hv.model.Customer;
import dev.hv.model.ICustomer;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.UUID;

@Path("customers")
public class CustomerController
{
    @POST
    @Path("customer")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCustomer(Customer customer) {
        customer.createCustomer();
        System.out.println("Customer erfolgreich erstellt");
        String message = "Customer erfolgreich erstellt";
        return Response.ok(message).build();
    }

    @GET
    @Path("customer/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerById(@PathParam("uuid") UUID uuid) {
        ICustomer customer = new Customer().getCustomerById(uuid);
        return Response.status(Response.Status.OK).entity(customer).build();
    }

    @PUT
    @Path("customer")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCustomer(Customer updatedCustomer) {
        ICustomer customer = new Customer().getCustomerById(updatedCustomer.getId());
        if (customer == null) {
            String message = "Customer existiert nicht";
            return Response.status(Response.Status.NOT_FOUND).entity(message).build();
        }
        new Customer().updateCustomer(updatedCustomer);
        return Response.status(Response.Status.OK).entity(updatedCustomer).build();
    }

    @DELETE
    @Path("customer/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCustomerById(@PathParam("uuid") UUID uuid) {
        ICustomer customer = new Customer().getCustomerById(uuid);
        if (customer == null) {
            String message = "Customer existiert nicht";
            return Response.status(Response.Status.NOT_FOUND).entity(message).build();
        }
        new Customer().deleteCustomerById(uuid);
        String message = "Customer wurde gelöscht";
        return Response.ok(message).build();
    }
}