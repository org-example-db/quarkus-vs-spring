package eu.davidemartorana.performance.spring.api;

import eu.davidemartorana.performance.spring.model.Message;
import eu.davidemartorana.performance.spring.services.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/random")
public class RandomControllerAPI {

    final private MessageService messageService;

    private static final Logger LOGGER = LoggerFactory.getLogger(RandomControllerAPI.class);

    @Autowired
    public RandomControllerAPI(final MessageService messageService){
        this.messageService = messageService;
    }

    @GetMapping("/date")
    public Message getRandomTime() {
        return this.messageService.generateRandomDate();
    }

    @GetMapping("/description")
    public Message getRandomString() {
        return this.messageService.generateRandomDescription();
    }

    @PostMapping("/format")
    public Message receiveMessage(@RequestBody final Message message) {
        LOGGER.trace("Received Message:\n{}", message);
        this.messageService.modifyingMessage(message);
        return message;
    }
}
