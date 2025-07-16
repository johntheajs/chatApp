package com.chatApp.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Calendar;
import java.util.UUID;

@Entity
public class ConversationParticipant {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(nullable = false)
    private UUID conversation_id;

    @Column(nullable = false)
    private UUID user_id;

    @Column(nullable = false)
    private Calendar joined_at;

    public ConversationParticipant(UUID conversation_id, UUID user_id, Calendar joined_at) {
        this.conversation_id = conversation_id;
        this.user_id = user_id;
        this.joined_at = joined_at;
    }

    public ConversationParticipant() {
    }

    public UUID getId() {
        return id;
    }

    public UUID getConversation_id() {
        return conversation_id;
    }

    public void setConversation_id(UUID conversation_id) {
        this.conversation_id = conversation_id;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    public Calendar getJoined_at() {
        return joined_at;
    }

    public void setJoined_at(Calendar joined_at) {
        this.joined_at = joined_at;
    }
}
