package eu.davidemartorana.performance.spring.api;

import eu.davidemartorana.performance.spring.model.Message;
import eu.davidemartorana.performance.spring.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/random")
public class RandomControllerAPI {

    final private MessageService messageService;

    @Autowired
    public RandomControllerAPI(final MessageService messageService){
        this.messageService = messageService;
    }

    @GetMapping("/time")
    public Message getRandomTime() {
        return this.messageService.generateRandomTime();
    }

    @GetMapping("/description")
    public Message getRandomString() {
        return this.messageService.generateRandomDescription();
    }

    @PostMapping("/format")
    public Message receiveMessage(final Message message) {
        this.messageService.modifyingMessage(message);
        return message;
    }
}
