package eu.davidemartorana.performance.spring.api;

import eu.davidemartorana.performance.spring.model.Message;
import eu.davidemartorana.performance.spring.services.RandomService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@RestController
@RequestMapping("/random")
public class RandomControllerAPI {

    final private RandomService randomService;
    final private AtomicBoolean booleanFlag = new AtomicBoolean(true);

    @Autowired
    public RandomControllerAPI(final RandomService randomService){
        this.randomService = randomService;
    }

    @GetMapping("/time")
    public Message getRandomTime() {
        return this.randomService.generateRandomTime();
    }

    @GetMapping("/description")
    public Message getRandomString() {
        return this.randomService.generateRandomDescription();
    }

    @PostMapping("/format")
    public Message receiveMessage(final Message message) {
        if(this.booleanFlag.getAndSet(false)) {
            message.setDateTime(LocalDateTime.now().minus(1, ChronoUnit.MONTHS));
        } else {
            this.booleanFlag.getAndSet(true);
            final int length = message.getDescription().length() + 12;
            final String msg = new StringBuilder()
                    .append(StringUtils.repeat("#", length))
                    .append("\n").append("##### ")
                    .append(message.getDescription())
                    .append("\n").append("##### ")
                    .append(StringUtils.repeat("#", length))
                    .toString();

            message.setDescription(msg);
        }
        message.setCode(200);
        return message;
    }


}
