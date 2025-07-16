package com.chatApp.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Calendar;
import java.util.UUID;

@Entity
public class Attachment {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(nullable = false)
    private UUID message_id;

    @Column(nullable = false)
    private String file_url;

    @Column(nullable = false)
    private String file_type;

    @Column(nullable = false)
    private Calendar uploaded_at;

    public Attachment(UUID message_id, String file_type, String file_url, Calendar uploaded_at) {
        this.message_id = message_id;
        this.file_type = file_type;
        this.file_url = file_url;
        this.uploaded_at = uploaded_at;
    }

    public Attachment() {
    }

    public UUID getId() {
        return id;
    }

    public UUID getMessage_id() {
        return message_id;
    }

    public void setMessage_id(UUID message_id) {
        this.message_id = message_id;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public Calendar getUploaded_at() {
        return uploaded_at;
    }

    public void setUploaded_at(Calendar uploaded_at) {
        this.uploaded_at = uploaded_at;
    }
}
