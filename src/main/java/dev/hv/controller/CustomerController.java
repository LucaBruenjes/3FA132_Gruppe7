package dev.hv.controller;

import dev.hv.dao.DAOCustomer;
import dev.hv.model.Customer;
import dev.hv.model.ICustomer;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.UUID;

@Path("/")
public class CustomerController {
    @POST
    @Path("customers")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCustomer(Customer customer) {
        try {
            customer.createCustomer();
        } catch (RuntimeException e) {
            String message = "Es wurde kein gültiges Objekt der Klasse 'Kunde' übermittelt.";
            return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
        }
        String message = "Customer erfolgreich erstellt";
        return Response.status(Response.Status.CREATED).entity(message).build();
    }

    @GET
    @Path("customers/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerById(@PathParam("uuid") UUID uuid) {
        try {
            ICustomer customer = new Customer().getCustomerById(uuid);
            return Response.status(Response.Status.OK).entity(customer).build();
        } catch (RuntimeException e) {
            String message = "Dieser Kunde existiert nicht";
            return Response.status(Response.Status.NOT_FOUND).entity(message).build();
        }
    }

    @GET
    @Path("customers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerList() {
        ArrayList<Customer> list = new DAOCustomer().getCustomerList();
        return Response.status(Response.Status.OK).entity(list).build();
    }

    @PUT
    @Path("customers")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateCustomer(Customer updatedCustomer) {
        ICustomer customer = new Customer().getCustomerById(updatedCustomer.getId());
        try {
            new Customer().updateCustomer(updatedCustomer);
            String message = "Kunde wurde erfolgreich aktualisiert";
            return Response.status(Response.Status.OK).entity(message).build();
        } catch (RuntimeException e) {
            if (customer == null) {
                String message = "Dieser Kunde existiert nicht";
                return Response.status(Response.Status.NOT_FOUND).entity(message).build();
            } else {
                String message = "Es wurde kein gültiges Objekt der Klasse 'Kunde' übermittelt.";
                return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
            }
        }
    }

    @DELETE
    @Path("customers/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCustomerById(@PathParam("uuid") UUID uuid) {
        try {
            ICustomer customer = new Customer().getCustomerById(uuid);
            new Customer().deleteCustomerById(uuid);
            return Response.status(Response.Status.OK).entity(customer).build();
        } catch (RuntimeException e) {
            String message = "Customer existiert nicht";
            return Response.status(Response.Status.NOT_FOUND).entity(message).build();
        }
    }
}