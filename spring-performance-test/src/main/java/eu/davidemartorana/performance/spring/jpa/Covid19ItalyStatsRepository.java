package eu.davidemartorana.performance.spring.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface Covid19ItalyStatsRepository extends JpaRepository<Covid19ItalyStats, Long> {

  @Query(value = "SELECT * FROM covid19_italy_stats WHERE date BETWEEN :start AND :end", nativeQuery = true)
  List<Covid19ItalyStats> findBetweenDates(final LocalDate start, final LocalDate end);
}
