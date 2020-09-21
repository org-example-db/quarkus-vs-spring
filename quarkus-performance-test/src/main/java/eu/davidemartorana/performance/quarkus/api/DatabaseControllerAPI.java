package eu.davidemartorana.performance.quarkus.api;

import eu.davidemartorana.performance.quarkus.jpa.Covid19ItalyStats;
import eu.davidemartorana.performance.quarkus.model.ISOLocalDate;
import eu.davidemartorana.performance.quarkus.services.Covid19DataService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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

        return  this.covid19DataService.getBetween(start, end);
    }

    @GET
    @Path("/italy-dates")
    public List<Covid19ItalyStats> getListWithDateParams(@QueryParam("start") ISOLocalDate start,
                                                              @QueryParam("end") ISOLocalDate end ) {

        return  this.covid19DataService.getBetween(start, end);
    }

    @GET
    @Path("/italy/{id}")
    public Covid19ItalyStats getById(@PathParam("id") Integer id) {
        return  this.covid19DataService.getById(id);
    }

}
