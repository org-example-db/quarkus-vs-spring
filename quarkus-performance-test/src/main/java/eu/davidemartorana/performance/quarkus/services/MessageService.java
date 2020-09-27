package eu.davidemartorana.performance.quarkus.services;

import eu.davidemartorana.performance.quarkus.model.Message;

public interface MessageService {

    Message generateRandomDate();

    Message generateRandomDescription();

    void modifyingMessage(Message message);
}
