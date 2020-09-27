package eu.davidemartorana.performance.quarkus.services.impl;

import eu.davidemartorana.performance.quarkus.model.Message;
import eu.davidemartorana.performance.quarkus.services.MessageService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Priority;
import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.time.temporal.ChronoUnit.DAYS;

@Singleton
@Alternative
@Priority(1)
public class MessageServiceImpl implements MessageService {

    private final static LocalDate START = LocalDate.of(2020,01,01);
    private final static AtomicBoolean BOOLEAN_FLAG = new AtomicBoolean(true);

    @Override
    public Message generateRandomDate() {
        final long gap = DAYS.between(START, LocalDate.now());
        final long random = RandomUtils.nextLong(0, gap);

        // Generate Random time between now and the beginning
        final LocalDate newRandomTime = DAYS.addTo(START,random);

        return  Message.builder()
                .description("Random Generated Time")
                .code(200)
                .dateTime(newRandomTime.atStartOfDay())
                .build();
    }


    @Override
    public Message generateRandomDescription(){
        return Message.builder()
                .code(200)
                .dateTime(LocalDateTime.now())
                .description(RandomStringUtils.random(10,true,true))
                .build();
    }

    @Override
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
