package dev.hv.controller;

import dev.hv.dao.DatabaseConnection;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/")
public class DatabaseController {
    @DELETE
    @Path("setupDB")
    public Response deleteTables() {
        new DatabaseConnection().removeAllTables();
        new DatabaseConnection().createAllTables();
        return Response.status(Response.Status.OK).build();
    }
}
