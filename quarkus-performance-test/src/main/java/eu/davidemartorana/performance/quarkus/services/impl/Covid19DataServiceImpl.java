package eu.davidemartorana.performance.quarkus.services.impl;

import eu.davidemartorana.performance.quarkus.jpa.Covid19ItalyStats;
import eu.davidemartorana.performance.quarkus.jpa.Covid19ItalyStatsRepository;
import eu.davidemartorana.performance.quarkus.services.Covid19DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class Covid19DataServiceImpl implements Covid19DataService {

    private static final Logger LOGGER = LoggerFactory.getLogger(Covid19DataServiceImpl.class);

    @Inject
    Covid19ItalyStatsRepository covid19ItalyStatsRepository;

    private static final LocalDate START_DATE = LocalDate.of(2020, 01,01);

    @Override
    public List<Covid19ItalyStats> getBetween(final LocalDate start, final LocalDate end) {
        final LocalDate startDate = getDateOrDefault(start, START_DATE);
        final LocalDate endDate = getDateOrDefault(end, LocalDate.now()).plusDays(1);

        LOGGER.debug("Retrieving data from '{}' to '{}'", startDate, endDate);

        return this.covid19ItalyStatsRepository.getBetween(startDate,endDate);
    }

    @Override
    public Optional<Covid19ItalyStats> getById(final int id){
        LOGGER.debug("Retrieving data by Id '{}'.", id);

        return  this.covid19ItalyStatsRepository.getById(id);
    }


    private LocalDate getDateOrDefault(final LocalDate date, final LocalDate defaultDate) {
        return  date == null ? defaultDate : date;
    }

}
