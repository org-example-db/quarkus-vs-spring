package eu.davidemartorana.performance.spring.services;

import eu.davidemartorana.performance.spring.jpa.Covid19ItalyStats;

import java.time.LocalDate;
import java.util.List;

public interface Covid19DataService {
    List<Covid19ItalyStats> getBetween(LocalDate start, LocalDate end);

    Covid19ItalyStats getById(long id);
}
