package com.chatApp.project.service;

import com.chatApp.project.exception.ConversationAlreadyExistsException;
import com.chatApp.project.model.Conversation;
import com.chatApp.project.repository.ConversationRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Service
public class ConversationService {

    @Autowired
    private ConversationRepo repo;

    public List<Conversation> getAllConversations(){
        return repo.findAll();
    }

    public Conversation getConversationById(UUID id){
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Conversation not found with ID: " + id));
    }

    public UUID addConversation(Conversation con){
        //Check if conversation exists
        if(repo.existsByName(con.getName())){
            throw new ConversationAlreadyExistsException(con.getName());
        }

        Calendar now = Calendar.getInstance();
        if(con.getCreated_at() == null){
            con.setCreated_at(now);
        }
        if(con.getLast_message_at() == null){
            con.setLast_message_at(now);
        }

        return repo.save(con).getId();

    }

    public void deleteConversation(UUID id){
        repo.deleteById(id);
    }

    public List<Conversation> searchConversationsByName(String name){
        return repo.findByNameContainingIgnoreCase(name);
    }

    public Conversation getConversationByName(String name){
        return repo.findByName(name);
    }


}
