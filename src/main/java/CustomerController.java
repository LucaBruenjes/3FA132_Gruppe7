import dev.hv.model.Customer;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@Path("customers")
public class CustomerController
{
    @GET
    @Path("customer")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomer() {
        Customer customer = new Customer();

        return Response.status(Response.Status.OK).entity(customer).build();
    }

    @POST
    @Path("customer")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addCustomer(Customer customer) {
        customer.createCustomer();
    }

    @PUT
    @Path("customer")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCustomer(Customer customer) {
        //ToDO Inhalt
        return null;
    }

    @GET
    @Path("customer/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerById(@PathParam("uuid") UUID uuid) {
        Customer customer = new Customer();
        return Response.status(Response.Status.OK).entity(customer).build();
    }

    @DELETE
    @Path("customer/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCustomerById(@PathParam("uuid") UUID uuid) {
        Customer customer = new Customer();
        return Response.status(Response.Status.OK).entity(customer).build();
    }
}