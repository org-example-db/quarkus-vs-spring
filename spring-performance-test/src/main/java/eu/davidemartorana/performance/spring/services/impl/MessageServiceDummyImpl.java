package eu.davidemartorana.performance.spring.services.impl;

import eu.davidemartorana.performance.spring.model.Message;
import eu.davidemartorana.performance.spring.services.MessageService;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceDummyImpl implements MessageService {


    @Override
    public Message generateRandomTime() {
        return null;
    }

    @Override
    public Message generateRandomDescription() {
        return null;
    }

    @Override
    public void modifyingMessage(Message message) {

    }
}
