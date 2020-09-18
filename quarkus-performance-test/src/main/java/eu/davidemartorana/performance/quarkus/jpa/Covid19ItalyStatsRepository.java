package eu.davidemartorana.performance.quarkus.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.NotFoundException;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class Covid19ItalyStatsRepository {

    private EntityManager entityManager;

    public Covid19ItalyStatsRepository(final EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public List<Covid19ItalyStats> getBetween(final LocalDate start, LocalDate end) {

        final Query query = entityManager.createNativeQuery("SELECT * \n" +
                "FROM covid19_italy_stats\n" +
                "WHERE date BETWEEN :start AND :end", Covid19ItalyStats.class);

        query.setParameter("start", start);
        query.setParameter("end", end);

        List<Covid19ItalyStats> result = query.getResultList();

        return result;
    }

    public Covid19ItalyStats getById(final int id){
        final Query query = entityManager.createNativeQuery("SELECT * \n" +
                "FROM covid19_italy_stats\n" +
                "WHERE id = :id ", Covid19ItalyStats.class);


        query.setParameter("id", id);

        List<Covid19ItalyStats> result = query.getResultList();

        if(result.isEmpty()) {
            throw new NotFoundException(String.format("Item with Id: [%s] not found.", id));
        }

        return result.get(0);

    }
}
