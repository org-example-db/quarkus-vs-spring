package eu.davidemartorana.performance.quarkus.api;

import eu.davidemartorana.performance.quarkus.model.Message;
import eu.davidemartorana.performance.quarkus.services.MessageService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/random")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RandomControllerAPI {

    @Inject
    protected MessageService messageService;

    @GET
    @Path("/time")
    public Message getRandomTime() {
        return this.messageService.generateRandomTime();
    }

    @GET
    @Path("/description")
    public Message getRandomString() {
        return this.messageService.generateRandomDescription();
    }

    @POST
    @Path("/format")
    public Message receiveMessage(final Message message) {
        this.messageService.modifyingMessage(message);
        return message;
    }


}
