package com.chatApp.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Entity
public class Conversation {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private Boolean is_group;

    @Column(nullable = false)
    private String name;

    private List<UUID> participants;

    @Column(nullable = false)
    private Calendar created_at;

    private Calendar last_message_at;

    public Conversation(Boolean is_group, String name, List<UUID> participants, Calendar created_at, Calendar last_message_at) {
        this.is_group = is_group;
        this.name = name;
        this.participants = participants;
        this.created_at = created_at;
        this.last_message_at = last_message_at;
    }

    public Conversation() {
    }

    public UUID getId() {
        return id;
    }

    public Boolean getIs_group() {
        return is_group;
    }

    public void setIs_group(Boolean is_group) {
        this.is_group = is_group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UUID> getParticipants() {
        return participants;
    }

    public void setParticipants(List<UUID> participants) {
        this.participants = participants;
    }

    public Calendar getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Calendar created_at) {
        this.created_at = created_at;
    }

    public Calendar getLast_message_at() {
        return last_message_at;
    }

    public void setLast_message_at(Calendar last_message_at) {
        this.last_message_at = last_message_at;
    }
}
