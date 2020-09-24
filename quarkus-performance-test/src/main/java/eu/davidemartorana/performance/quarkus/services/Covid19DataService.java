package eu.davidemartorana.performance.quarkus.services;

import eu.davidemartorana.performance.quarkus.jpa.Covid19ItalyStats;

import java.time.LocalDate;
import java.util.List;

public interface Covid19DataService {

    List<Covid19ItalyStats> getBetween(LocalDate start, LocalDate end);

    Covid19ItalyStats getById(int id);
}
