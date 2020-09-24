package eu.davidemartorana.performance.quarkus.converters;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

@Provider
public class LocalDateParamConverterProvider implements ParamConverterProvider {

    private final LocalDateParamConverter INSTANCE = new LocalDateParamConverter();

    @Override
    public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
        if(rawType.isAssignableFrom(LocalDate.class)) {
            final Optional<Annotation> dateTimeFormatAnnotation = Arrays.stream(annotations)
                    .filter(annotation -> annotation.annotationType().isAssignableFrom(DateTimeFormat.class))
                    .findFirst();

            if(dateTimeFormatAnnotation.isPresent()) {
                final String pattern = ((DateTimeFormat) dateTimeFormatAnnotation.get()).pattern();
                return new LocalDateParamConverter().setDateTimeFormatter(pattern);

            }
            return INSTANCE;
        }

        return null;
    }
}
