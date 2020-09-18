package eu.davidemartorana.performance.quarkus.api;

import javax.ws.rs.BadRequestException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ISOLocalDate {

    private final LocalDate localDate;

    /**
     * Accepts string value in ISO Format returns an instance of this class.
     *
     * @param value - string to be converted in {@link LocalDate}
     * @return an instance of this class
     */
    public static ISOLocalDate valueOf(final String value){
        final LocalDate tmpLocalDate;
        try {
            tmpLocalDate = LocalDate.parse(value, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (final DateTimeParseException e) {
            throw new BadRequestException(String.format("Date value [%s] is not valid. Please provide a valid value.", value) ,e);
        }

        return new ISOLocalDate(tmpLocalDate);

    }

    private ISOLocalDate(final LocalDate value){
        this.localDate = value;
    }

    /**
     * Returns the {@link LocalDate} instance related to the initial string value
     *
     * @return the {@link LocalDate} instance related to the initial string value
     */
    public LocalDate getValue(){
        return this.localDate;
    }

}
