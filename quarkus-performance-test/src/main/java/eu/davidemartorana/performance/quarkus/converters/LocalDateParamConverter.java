package eu.davidemartorana.performance.quarkus.converters;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.ext.ParamConverter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateParamConverter implements ParamConverter {


    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
    private String pattern = "yyyy-MM-dd";

    public LocalDateParamConverter setDateTimeFormatter(final String pattern) {
        this.dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        this.pattern = pattern;

        return this;
    }


    @Override
    public Object fromString(String value) {
        try {
            return LocalDate.parse(value, dateTimeFormatter);
        } catch (final DateTimeParseException e) {
            throw new BadRequestException(String.format("Date value [%s] is not valid. Please provide a valid value that follows the pattern: [%s].", value, pattern) ,e);
        }

    }

    @Override
    public String toString(Object value) {
        if(value instanceof LocalDate){
            final LocalDate localDate = (LocalDate) value;
            return localDate.format(dateTimeFormatter);
        }
        throw new IllegalArgumentException("Given object is not instance of LocalDate.");
    }
}
