package eu.davidemartorana.performance.quarkus.services;

import eu.davidemartorana.performance.quarkus.model.Message;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.time.temporal.ChronoUnit.MINUTES;

@ApplicationScoped
public class MessageService {

    private final static LocalDateTime START = LocalDateTime.of(1970,01,01, 0, 0);
    private final static AtomicBoolean BOOLEAN_FLAG = new AtomicBoolean(true);

    public Message generateRandomTime() {
        final long gap = MINUTES.between(START, LocalDateTime.now());
        final long random = RandomUtils.nextLong(0, gap);

        // Generate Random time between now and the beginning
        final LocalDateTime newRandomTime = MINUTES.addTo(START,random);

        return  Message.builder()
                .description("Random Generated Time")
                .code(200)
                .dateTime(newRandomTime)
                .build();
    }


    public Message generateRandomDescription(){
        return Message.builder()
                .code(200)
                .dateTime(LocalDateTime.now())
                .description(RandomStringUtils.random(10,true,true))
                .build();
    }

    public void modifyingMessage(final Message message) {
        if(BOOLEAN_FLAG.getAndSet(false)) {
            message.setDateTime(LocalDateTime.now().minus(1, ChronoUnit.MONTHS));
        } else {
            BOOLEAN_FLAG.getAndSet(true);
            final int length = message.getDescription().length() + 12;
            final String msg = new StringBuilder()
                    .append(StringUtils.repeat("#", length))
                    .append("\n").append("##### ")
                    .append(message.getDescription())
                    .append(" #####").append("\n")
                    .append(StringUtils.repeat("#", length))
                    .toString();

            message.setDescription(msg);
        }
        message.setCode(200);
    }

}
