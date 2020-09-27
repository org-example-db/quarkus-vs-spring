package eu.davidemartorana.performance.quarkus.jpa;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "covid19_italy_stats")
@Data
public class Covid19ItalyStats {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name="date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Column(name="country")
    private String country;

    @Column(name="hospitalized_patients_symptoms")
    private String hospitalizedPatientsSymptoms;

    @Column(name="hospitalized_patients_intensive_care")
    private Integer hospitalizedPatientsIntensiveCare;

    @Column(name="total_hospitalized_patients")
    private Integer totalHospitalizedPatients;

    @Column(name="home_confinement_cases")
    private Integer homeConfinementCases;

    @Column(name="total_current_confirmed_cases")
    private Integer totalCurrentConfirmedCases;

    @Column(name="new_current_confirmed_cases")
    private Integer newCurrentConfirmedCases;

    @Column(name="new_total_confirmed_cases")
    private Integer newTotalConfirmedCases;

    @Column(name="recovered")
    private Integer recovered;

    @Column(name="deaths")
    private Integer deaths;

    @Column(name="total_confirmed_cases")
    private Integer totalConfirmedCases;

    @Column(name="tests_performed")
    private Integer testsPerformed;

    @Column(name="note")
    private String note;
    
}
