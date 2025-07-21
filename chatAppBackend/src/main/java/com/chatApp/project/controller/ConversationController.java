package com.chatApp.project.controller;

import com.chatApp.project.model.Conversation;
import com.chatApp.project.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/con")
public class ConversationController {

    @Autowired
    private ConversationService service;

    @GetMapping
    public List<Conversation> getAllConversations(){
        return service.getAllConversations();
    }

    @GetMapping("/{id}")
    public Conversation getConversationById(@PathVariable UUID id){
        return service.getConversationById(id);
    }

    @PostMapping
    public UUID createConversation(@RequestBody Conversation con){
        return service.addConversation(con);
    }

    @DeleteMapping("/{id}")
    public String deleteConversation(@PathVariable UUID id){
        service.deleteConversation(id);
        return "Deleted Conversation successfully!";
    }

    @GetMapping("/search")
    public List<Conversation> searchConversationByName(@RequestParam String name){
        return service.searchConversationsByName(name);
    }

    @PutMapping("/update")
    public String updateParticipants(@RequestBody Conversation con){
        try{
            service.updateParticipants(con);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Conversation updated " + con.getName() + " successfully!";
    }

}
