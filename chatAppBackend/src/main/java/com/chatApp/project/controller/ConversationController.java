package com.chatApp.project.controller;

import com.chatApp.project.model.Conversation;
import com.chatApp.project.service.ConversationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/con")
@Tag(name = "Conversation API", description = "APIs for managing the Conversations")
public class ConversationController {

    @Autowired
    private ConversationService service;

    @GetMapping
    @Operation(summary = "List all conversations", description = "Get all the conversation info")
    public List<Conversation> getAllConversations(){
        return service.getAllConversations();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Conversation by ID", description = "Get the specific conversation by ID in PathVariable")
    public Conversation getConversationById(@PathVariable UUID id){
        return service.getConversationById(id);
    }

    @PostMapping
    @Operation(summary = "Create Conversation", description = "Add a new conversation with RequestBody")
    public UUID createConversation(@RequestBody Conversation con){
        return service.addConversation(con);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete the conversation", description = "Delete a specific conversation with ID in PathVariable")
    public String deleteConversation(@PathVariable UUID id){
        service.deleteConversation(id);
        return "Deleted Conversation successfully!";
    }

    @GetMapping("/search")
    @Operation(summary = "Get Conversation by name", description = "Search for conversation with name in RequestParam")
    public List<Conversation> searchConversationByName(@RequestParam String name){
        return service.searchConversationsByName(name);
    }

    @PutMapping("/update")
    @Operation(summary = "Update Conversation", description = "Update the conversation with RequestBody")
    public String updateParticipants(@RequestBody Conversation con){
        try{
            service.updateParticipants(con);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Conversation updated " + con.getName() + " successfully!";
    }

}
