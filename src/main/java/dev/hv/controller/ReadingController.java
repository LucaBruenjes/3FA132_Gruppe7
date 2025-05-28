package dev.hv.controller;

import dev.hv.model.Reading;
import dev.hv.model.IReading;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@Path("readings")
public class ReadingController {
    @POST
    @Path("reading")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addReading(Reading reading) {
        reading.createReading();
        System.out.println("Reading erfolgreich erstellt");
        String message = "Reading erfolgreich erstellt";
        return Response.ok(message).build();
    }


}
