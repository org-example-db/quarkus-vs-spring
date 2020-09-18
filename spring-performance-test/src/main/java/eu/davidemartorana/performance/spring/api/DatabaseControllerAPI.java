package eu.davidemartorana.performance.spring.api;

import eu.davidemartorana.performance.spring.jpa.Covid19ItalyStats;
import eu.davidemartorana.performance.spring.services.Covid19DataService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/covid19")
public class DatabaseControllerAPI {

    private final Covid19DataService covid19DataService;

    public DatabaseControllerAPI(final Covid19DataService covid19DataService){
        this.covid19DataService = covid19DataService;
    }

    @GetMapping("/italy")
    public List<Covid19ItalyStats> getListWithParams(@RequestParam(value = "start", required = false) @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDate start,
                                                     @RequestParam(value = "end", required = false) @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDate end ) {

        final LocalDate startDate = start == null ? LocalDate.of(2020, 01,01): start;
        final LocalDate endDate = end == null ? LocalDate.now().plusDays(1) : end;

        return  this.covid19DataService.getBetween(startDate, endDate);
    }

    @GetMapping("/italy/{id}")
    public Covid19ItalyStats getById(@PathParam("id") Integer id) {
        return  this.covid19DataService.getById(id);
    }

}
