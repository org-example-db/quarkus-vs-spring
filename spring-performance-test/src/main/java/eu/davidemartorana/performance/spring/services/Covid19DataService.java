package eu.davidemartorana.performance.spring.services;

import eu.davidemartorana.performance.spring.jpa.Covid19ItalyRepository;
import eu.davidemartorana.performance.spring.jpa.Covid19ItalyStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class Covid19DataService {


    private final Covid19ItalyRepository covid19ItalyRepository;
    @Autowired
    public Covid19DataService(final Covid19ItalyRepository covid19ItalyRepository) {
        this.covid19ItalyRepository = covid19ItalyRepository;
    }

    public List<Covid19ItalyStats> getBetween(final LocalDate start, LocalDate end) {

        return this.covid19ItalyRepository.findBetweenDates(start,end);
    }

    public Covid19ItalyStats getById(final long id){
        final Optional<Covid19ItalyStats> optionalResult = this.covid19ItalyRepository.findById(id);

        return optionalResult.orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, String.format("Item with Id: [%s] not found.", id)));
    }

}
