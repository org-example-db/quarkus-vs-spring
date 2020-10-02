package eu.davidemartorana.performance.quarkus.jpa;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class Covid19ItalyStatsRepository implements PanacheRepository<Covid19ItalyStats> {

    public List<Covid19ItalyStats> getBetween(final LocalDate start, LocalDate end) {
        return list("date BETWEEN :start AND :end", Parameters.with("start",start).and("end", end));
    }

    public Optional<Covid19ItalyStats> getById(final long id){
        return findByIdOptional(id);

    }
}
