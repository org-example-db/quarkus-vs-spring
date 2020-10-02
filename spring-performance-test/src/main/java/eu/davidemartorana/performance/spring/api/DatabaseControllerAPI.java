package eu.davidemartorana.performance.spring.api;

import eu.davidemartorana.performance.spring.jpa.Covid19ItalyStats;
import eu.davidemartorana.performance.spring.services.Covid19DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.websocket.server.PathParam;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/covid19")
public class DatabaseControllerAPI {

    private final Covid19DataService covid19DataService;

    public DatabaseControllerAPI(final Covid19DataService covid19DataService){
        this.covid19DataService = covid19DataService;
    }

    @GetMapping("/italy")
    public List<Covid19ItalyStats> getListWithParams(@RequestParam(value = "start", required = false, defaultValue = "2020-01-01") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDate start,
                                                     @RequestParam(value = "end", required = false) @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDate end ) {

        return  this.covid19DataService.getBetween(start, end);
    }

    @GetMapping("/italy/{id}")
    public Covid19ItalyStats getById(@PathVariable("id") long id) {
        final Optional<Covid19ItalyStats> optionalResult = this.covid19DataService.getById(id);

        return optionalResult
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND,
                        String.format("Item with Id: [%s] not found.", id)));
    }

}
