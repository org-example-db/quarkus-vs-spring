package eu.davidemartorana.performance.spring.services;

import eu.davidemartorana.performance.spring.model.Message;

public interface MessageService {

    Message generateRandomTime();

    Message generateRandomDescription();

    void modifyingMessage(Message message);
}
