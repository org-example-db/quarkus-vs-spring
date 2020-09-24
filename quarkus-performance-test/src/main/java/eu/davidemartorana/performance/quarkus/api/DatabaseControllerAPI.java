package eu.davidemartorana.performance.quarkus.api;

import eu.davidemartorana.performance.quarkus.converters.DateTimeFormat;
import eu.davidemartorana.performance.quarkus.jpa.Covid19ItalyStats;
import eu.davidemartorana.performance.quarkus.services.Covid19DataService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
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
    public List<Covid19ItalyStats> getListWithDateParams(@QueryParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd") @DefaultValue("2020-01-01") LocalDate start,
                                                         @QueryParam("end") LocalDate end ) {

        return  this.covid19DataService.getBetween(start, end);
    }

    @GET
    @Path("/italy/{id}")
    public Covid19ItalyStats getById(@PathParam("id") Integer id) {
        return  this.covid19DataService.getById(id);
    }

}
