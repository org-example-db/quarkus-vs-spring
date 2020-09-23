package eu.davidemartorana.performance.spring.services.impl;

import eu.davidemartorana.performance.spring.model.Message;
import eu.davidemartorana.performance.spring.services.MessageService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.time.temporal.ChronoUnit.MINUTES;

@Service
@Primary
public class MessageServiceImpl implements MessageService {

    private final static LocalDateTime START = LocalDateTime.of(1970,01,01, 0, 0);
    private final static AtomicBoolean BOOLEAN_FLAG = new AtomicBoolean(true);

    @Override
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
