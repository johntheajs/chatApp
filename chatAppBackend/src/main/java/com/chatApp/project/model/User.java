package com.chatApp.project.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Calendar;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(nullable = false, unique = true)
    private String username;

    private String email;

    @Column(nullable = false)
    private String hashed_password;

    private String avatar_url;

    @JsonProperty("is_online")
    private Boolean isOnline;

    private Calendar last_seen;
    private Calendar created_at;

    public User(String username, String email, String hashed_password, String avatar_url, Boolean is_online, Calendar last_seen, Calendar created_at) {
        this.username = username;
        this.email = email;
        this.hashed_password = hashed_password;
        this.avatar_url = avatar_url;
        this.isOnline = is_online;
        this.last_seen = last_seen;
        this.created_at = created_at;
    }

    public User(String username, String email, String hashed_password, Boolean is_online, Calendar last_seen, Calendar created_at) {
        this.username = username;
        this.email = email;
        this.hashed_password = hashed_password;
        this.isOnline = is_online;
        this.last_seen = last_seen;
        this.created_at = created_at;
    }

    public User() {
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashed_password() {
        return hashed_password;
    }

    public void setHashed_password(String hashed_password) {
        this.hashed_password = hashed_password;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    @JsonProperty("is_online")
    public Boolean getOnline() {
        return isOnline;
    }

    @JsonProperty("is_online")
    public void setOnline(Boolean online) {
        isOnline = online;
    }

    public Calendar getLast_seen() {
        return last_seen;
    }

    public void setLast_seen(Calendar last_seen) {
        this.last_seen = last_seen;
    }

    public Calendar getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Calendar created_at) {
        this.created_at = created_at;
    }




}
