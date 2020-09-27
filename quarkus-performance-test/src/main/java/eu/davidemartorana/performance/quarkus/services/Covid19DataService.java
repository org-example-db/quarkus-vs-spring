package eu.davidemartorana.performance.quarkus.services;

import eu.davidemartorana.performance.quarkus.jpa.Covid19ItalyStats;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface Covid19DataService {

    List<Covid19ItalyStats> getBetween(LocalDate start, LocalDate end);

    Optional<Covid19ItalyStats> getById(int id);
}
