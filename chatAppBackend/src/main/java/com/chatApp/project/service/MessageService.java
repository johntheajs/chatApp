package com.chatApp.project.service;

import com.chatApp.project.exception.ConversationDoesNotExistException;
import com.chatApp.project.model.Message;
import com.chatApp.project.repository.ConversationRepo;
import com.chatApp.project.repository.MessageRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Service
public class MessageService {

    @Autowired
    private MessageRepo repo;
    @Autowired
    private ConversationRepo conRepo;

    public List<Message> getAllMessagesFromAConversation(UUID id){
        if(!(conRepo.existsById(id))){
            throw new ConversationDoesNotExistException(id);
        }
        return repo.findByConversationId(id);
    }

    public Message getMessageWithId(UUID id){
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Message not found with ID: " + id));
    }

    public UUID createMessage(Message mes){
        Calendar now = Calendar.getInstance();
        if(mes.getCreated_at() == null){
            mes.setCreated_at(now);
        }
        return repo.save(mes).getId();
    }

    public Message editMessage(Message updatedMes){
        Message mes = repo.findById(updatedMes.getId()).orElseThrow(() -> new EntityNotFoundException("Message not found with ID: " + updatedMes.getId()));
        mes.setIs_edited(true);
        mes.setContent(updatedMes.getContent());
        mes.setIs_read(false);
        repo.save(mes);
        return mes;
    }

    public String deleteMessage(UUID id){
        repo.deleteById(id);
        return "Message with ID-" + id + " ,delete successfully!";
    }

    public Message messageIsRead(UUID id){
        Message mes = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Message not found with ID: " + id));
        mes.setIs_read(true);
        repo.save(mes);
        return mes;
    }

}
