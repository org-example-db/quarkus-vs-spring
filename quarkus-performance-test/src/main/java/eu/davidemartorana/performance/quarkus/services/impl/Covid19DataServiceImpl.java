package eu.davidemartorana.performance.quarkus.services.impl;

import eu.davidemartorana.performance.quarkus.model.ISOLocalDate;
import eu.davidemartorana.performance.quarkus.jpa.Covid19ItalyStats;
import eu.davidemartorana.performance.quarkus.jpa.Covid19ItalyStatsRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@ApplicationScoped
public class Covid19DataServiceImpl implements eu.davidemartorana.performance.quarkus.services.Covid19DataService {

    private static final Logger LOGGER = LoggerFactory.getLogger(Covid19DataServiceImpl.class);

    @Inject
    Covid19ItalyStatsRepository covid19ItalyStatsRepository;

    private static final LocalDate START_DATE = LocalDate.of(2020, 01,01);

    @Override
    public List<Covid19ItalyStats> getBetween(final ISOLocalDate start, final ISOLocalDate end) {
        final LocalDate startDate = getDateOrDefault(start, START_DATE);
        final LocalDate endDate = getDateOrDefault(end, LocalDate.now().plusDays(1));

        LOGGER.debug("Retrieving data from '{}' to '{}'", startDate, endDate);

        return this.covid19ItalyStatsRepository.getBetween(startDate,endDate);
    }

    @Override
    public List<Covid19ItalyStats> getBetween(final String start, final String end) {
        final LocalDate startDate = getDateOrDefault(start, START_DATE);
        final LocalDate endDate = getDateOrDefault(end, LocalDate.now().plusDays(1));

        LOGGER.debug("Retrieving data from '{}' to '{}'", startDate, endDate);

        return this.covid19ItalyStatsRepository.getBetween(startDate,endDate);
    }

    @Override
    public Covid19ItalyStats getById(final int id){
        LOGGER.debug("Retrieving data by Id '{}'.", id);

        return  this.covid19ItalyStatsRepository.getById(id);
    }



    private LocalDate getDateOrDefault(final String date, final LocalDate defaultDate) {
        try {
            return StringUtils.isEmpty(date) ? defaultDate : LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (final DateTimeParseException e) {
            throw new BadRequestException(String.format("Date value [%s] is not valid. Please provide a valid value.", date) ,e);
        }
    }

    private LocalDate getDateOrDefault(final ISOLocalDate date, final LocalDate defaultDate) {
        return  date == null ? defaultDate : date.getValue();
    }

}
