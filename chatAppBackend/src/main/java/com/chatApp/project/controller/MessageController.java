package com.chatApp.project.controller;

import com.chatApp.project.model.Message;
import com.chatApp.project.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/mes")
public class MessageController {

    @Autowired
    private MessageService service;

    @GetMapping
    public Message getMessageByID(@RequestParam UUID id){
        return service.getMessageWithId(id);
    }

    @GetMapping("/{id}")
    public List<Message> getAllMessagesWithConID(@PathVariable UUID id){
        return service.getAllMessagesFromAConversation(id);
    }

    @PostMapping
    public UUID createMessage(@RequestBody Message mes){
        return service.createMessage(mes);
    }

    @PutMapping
    public Message editMessage(@RequestBody Message mes){
        return service.editMessage(mes);
    }

    @DeleteMapping("/{id}")
    public String deleteMessage(@PathVariable UUID id){
        return service.deleteMessage(id);
    }

    @PutMapping("/read/{id}")
    public Message messageIsRead(@PathVariable UUID id){
        return service.messageIsRead(id);
    }
}
