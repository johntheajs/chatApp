package com.chatApp.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Calendar;
import java.util.UUID;

@Entity
public class Message {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(nullable = false)
    private UUID sender_id;

    @Column(nullable = false)
    private UUID conversation_id;

    @Column(nullable = false)
    private String content;

    private Calendar created_at;
    private Boolean is_read;

    public Message(UUID sender_id, UUID conversation_id, String content, Calendar created_at, Boolean is_read) {
        this.sender_id = sender_id;
        this.conversation_id = conversation_id;
        this.content = content;
        this.created_at = created_at;
        this.is_read = is_read;
    }

    public Message() {
    }

    public UUID getId() {
        return id;
    }

    public UUID getSender_id() {
        return sender_id;
    }

    public void setSender_id(UUID sender_id) {
        this.sender_id = sender_id;
    }

    public UUID getConversation_id() {
        return conversation_id;
    }

    public void setConversation_id(UUID conversation_id) {
        this.conversation_id = conversation_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Calendar getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Calendar created_at) {
        this.created_at = created_at;
    }

    public Boolean getIs_read() {
        return is_read;
    }

    public void setIs_read(Boolean is_read) {
        this.is_read = is_read;
    }
}
