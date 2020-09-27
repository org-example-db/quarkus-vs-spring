package eu.davidemartorana.performance.quarkus.services.impl;

import eu.davidemartorana.performance.quarkus.model.Message;
import eu.davidemartorana.performance.quarkus.services.MessageService;

import javax.inject.Singleton;

@Singleton
public class MessageServiceDummyImpl implements MessageService {


    @Override
    public Message generateRandomDate() {
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
