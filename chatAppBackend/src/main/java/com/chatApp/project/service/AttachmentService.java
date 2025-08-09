package com.chatApp.project.service;

import com.azure.storage.blob.*;
import com.azure.storage.blob.models.*;
import com.chatApp.project.model.Attachment;
import com.chatApp.project.repository.AttachmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

@Service
public class AttachmentService {

    @Autowired
    private final AttachmentRepo repo;
    @Autowired
    private final BlobContainerClient containerClient;


    public AttachmentService(AttachmentRepo repo, BlobContainerClient containerClient) {
        this.repo = repo;
        this.containerClient = containerClient;
    }



    public Attachment uploadAttachment(UUID messageId, MultipartFile file) throws IOException {
        String blobName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        BlobClient blobClient = containerClient.getBlobClient(blobName);

        blobClient.upload(file.getInputStream(), file.getSize(), true);

        Attachment attachment = new Attachment();
        attachment.setMessage_id(messageId);
        attachment.setFile_type(file.getContentType());
        attachment.setFile_url(blobClient.getBlobUrl());
        attachment.setUploaded_at(Calendar.getInstance());

        return repo.save(attachment);
    }
}
