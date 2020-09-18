package eu.davidemartorana.performance.quarkus.api;

import eu.davidemartorana.performance.quarkus.jpa.Covid19ItalyStats;
import eu.davidemartorana.performance.quarkus.services.Covid19DataService;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Path("/covid19")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DatabaseControllerAPI {

    private final Covid19DataService covid19DataService;

    public DatabaseControllerAPI(final Covid19DataService covid19DataService){
        this.covid19DataService = covid19DataService;
    }

    @GET
    @Path("/italy")
    public List<Covid19ItalyStats> getListWithParams(@QueryParam("start") String start,
                                                     @QueryParam("end") String end ) {

        final LocalDate startDate = getDateOrDefault(start, LocalDate.of(2020, 01,01));
        final LocalDate endDate = getDateOrDefault(end, LocalDate.now().plusDays(1));

        return  this.covid19DataService.getBetween(startDate, endDate);
    }

    @GET
    @Path("/italy/{id}")
    public Covid19ItalyStats getById(@PathParam("id") Integer id) {
        return  this.covid19DataService.getById(id);
    }

    private LocalDate getDateOrDefault(final String date, final LocalDate defaultDate) {
        try {
            return StringUtils.isEmpty(date) ? defaultDate : LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (final DateTimeParseException e) {
            throw new BadRequestException(String.format("Date value [%s] is not valid. Please provide a valid value.", date) ,e);
        }
    }

}
