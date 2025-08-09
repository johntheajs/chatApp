package com.chatApp.project.controller;

import com.chatApp.project.model.Message;
import com.chatApp.project.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/mes")
@Tag(name = "Message API", description = "APIs to manage the messages")
public class MessageController {

    @Autowired
    private MessageService service;

    @GetMapping
    @Operation(summary = "Get Message by ID", description = "Get a Message with ID in RequestParam")
    public Message getMessageByID(@RequestParam UUID id){
        return service.getMessageWithId(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Messages of a Conversation", description = "Get all messages of a conversation with ID in PathVariable")
    public List<Message> getAllMessagesWithConID(@PathVariable UUID id){
        return service.getAllMessagesFromAConversation(id);
    }

    @PostMapping
    @Operation(summary = "Create a Message", description = "Create message with RequestBody")
    public UUID createMessage(@RequestBody Message mes){
        return service.createMessage(mes);
    }

    @PutMapping
    @Operation(summary = "Update the message", description = "Update the message with object in RequestBody")
    public Message editMessage(@RequestBody Message mes){
        return service.editMessage(mes);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete the Message", description = "Delete the message with ID in PathVariable")
    public String deleteMessage(@PathVariable UUID id){
        return service.deleteMessage(id);
    }

    @PutMapping("/read/{id}")
    @Operation(summary = "Confirm message read", description = "Change message IsRead")
    public Message messageIsRead(@PathVariable UUID id){
        return service.messageIsRead(id);
    }
}
