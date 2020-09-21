package eu.davidemartorana.performance.quarkus.services;

import eu.davidemartorana.performance.quarkus.jpa.Covid19ItalyStats;
import eu.davidemartorana.performance.quarkus.model.ISOLocalDate;

import java.util.List;

public interface Covid19DataService {
    List<Covid19ItalyStats> getBetween(ISOLocalDate start, ISOLocalDate end);

    List<Covid19ItalyStats> getBetween(String start, String end);

    Covid19ItalyStats getById(int id);
}
