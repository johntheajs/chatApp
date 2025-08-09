package com.chatApp.project.controller;

import com.chatApp.project.model.Attachment;
import com.chatApp.project.service.AttachmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/attachments")
@Tag(name="Attachment API", description = "APIs for managing the attachments in the message")
public class AttachmentController {

    private final AttachmentService service;

    public AttachmentController(AttachmentService service) {
        this.service = service;
    }

    @PostMapping("/upload")
    @Operation(summary = "Upload Attachment", description = "Uploads the file and stores the link with RequestParam")
    public ResponseEntity<Attachment> uploadAttachment(
            @RequestParam UUID messageId,
            @RequestParam MultipartFile file) throws IOException {
        return ResponseEntity.ok(service.uploadAttachment(messageId, file));
    }
}
