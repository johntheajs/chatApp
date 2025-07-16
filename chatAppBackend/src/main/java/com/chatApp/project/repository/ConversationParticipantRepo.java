package com.chatApp.project.repository;

import com.chatApp.project.model.ConversationParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ConversationParticipantRepo extends JpaRepository<ConversationParticipant, UUID> {
}
