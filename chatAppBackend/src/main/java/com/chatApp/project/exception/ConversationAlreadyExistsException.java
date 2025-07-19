package com.chatApp.project.exception;

public class ConversationAlreadyExistsException extends RuntimeException {
    public ConversationAlreadyExistsException(String conversation) {

        super("Conversation already exists " + conversation);
    }
}
