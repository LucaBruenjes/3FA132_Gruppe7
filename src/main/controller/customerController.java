


@Path ("customers")
public class customerController
{
    @GET
    @Path("customer")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomer() {
        Customer customer = new Customer;
        return Response.status(Response.Status.OK).entity(customer).build();
    }

    @POST
    @Path("customer")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCustomer(Customer customer) {
        //ToDO Inhalt
    }

    @PUT
    @Path("customer")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCustomer(Customer customer) {
        //ToDO Inhalt
    }

    @GET
    @Path("customer/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerById(@PathParam("uuid") UUID uuid) {
        Customer customer = new Customer;
        return Response.status(Response.Status.OK).entity(customer).build();
    }

    @DELETE
    @Path("customer/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCustomerById(@PathParam("uuid") UUID uuid) {
        Customer customer = new Customer;
        return Response.status(Response.Status.OK).entity(customer).build();
    }
}