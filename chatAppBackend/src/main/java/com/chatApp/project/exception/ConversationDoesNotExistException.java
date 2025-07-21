package com.chatApp.project.exception;

import java.util.UUID;

public class ConversationDoesNotExistException extends RuntimeException {
    public ConversationDoesNotExistException(UUID id) {

        super("Conversation does not exist with ID as " + id);
    }
}
