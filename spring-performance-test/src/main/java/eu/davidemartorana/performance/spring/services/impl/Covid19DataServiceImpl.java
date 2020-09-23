package eu.davidemartorana.performance.spring.services.impl;

import eu.davidemartorana.performance.spring.jpa.Covid19ItalyStats;
import eu.davidemartorana.performance.spring.jpa.Covid19ItalyStatsRepository;
import eu.davidemartorana.performance.spring.services.Covid19DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class Covid19DataServiceImpl implements Covid19DataService {

    private static final Logger LOGGER = LoggerFactory.getLogger(Covid19DataServiceImpl.class);

    @Autowired
    private Covid19ItalyStatsRepository covid19ItalyStatsRepository;

    private static final LocalDate START_DATE = LocalDate.of(2020, 01,01);

    @Override
    public List<Covid19ItalyStats> getBetween(final LocalDate start, final LocalDate end) {
        final LocalDate startDate = getDateOrDefault(start, START_DATE);
        final LocalDate endDate = getDateOrDefault(end, LocalDate.now().plusDays(1));

        LOGGER.debug("Retrieving data from '{}' to '{}'", startDate, endDate);

        return this.covid19ItalyStatsRepository.findBetweenDates(startDate,endDate);
    }

    @Override
    public Covid19ItalyStats getById(final long id) {
        LOGGER.debug("Retrieving data by Id '{}'.", id);
        final Optional<Covid19ItalyStats> optionalResult = this.covid19ItalyStatsRepository.findById(id);

        return optionalResult
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND,
                        String.format("Item with Id: [%s] not found.", id)));
    }

    private LocalDate getDateOrDefault(final LocalDate date, final LocalDate defaultDate) {
        return  date == null ? defaultDate : date;
    }

}
