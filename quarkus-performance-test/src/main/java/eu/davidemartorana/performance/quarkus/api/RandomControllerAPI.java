package eu.davidemartorana.performance.quarkus.api;

import eu.davidemartorana.performance.quarkus.model.Message;
import eu.davidemartorana.performance.quarkus.services.RandomService;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@Path("/random")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RandomControllerAPI {

    final private RandomService randomService;
    final private AtomicBoolean booleanFlag = new AtomicBoolean(true);

    public RandomControllerAPI(final RandomService randomService){
        this.randomService = randomService;
    }

    @GET
    @Path("/time")
    public Message getRandomTime() {
        return this.randomService.generateRandomTime();
    }

    @GET
    @Path("/description")
    public Message getRandomString() {
        return this.randomService.generateRandomDescription();
    }

    @POST
    @Path("/format")
    public Message receiveMessage(final Message message) {
        if(this.booleanFlag.getAndSet(false)) {
            message.setDateTime(LocalDateTime.now().minus(1, ChronoUnit.MONTHS));
        } else {
            this.booleanFlag.getAndSet(true);
            final int length = message.getDescription().length() + 12;
            final String msg = new StringBuilder()
                    .append(StringUtils.repeat("#", length))
                    .append("\n").append("##### ")
                    .append(message.getDescription())
                    .append("\n").append("##### ")
                    .append(StringUtils.repeat("#", length))
                    .toString();

            message.setDescription(msg);
        }
        message.setCode(200);
        return message;
    }


}
