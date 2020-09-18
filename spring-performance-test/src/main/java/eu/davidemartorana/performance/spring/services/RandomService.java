package eu.davidemartorana.performance.spring.services;

import eu.davidemartorana.performance.spring.model.Message;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.MINUTES;


@Component
public class RandomService {

    private final LocalDateTime start = LocalDateTime.of(1970,01,01, 0, 0);


    public Message generateRandomTime() {
        final long gap = MINUTES.between(start, LocalDateTime.now());
        final long random = RandomUtils.nextLong(0, gap);

        // Generate Random time between now and the beginning
        final LocalDateTime newRandomTime = MINUTES.addTo(start,random);

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

}
