package com.chatApp.project.repository;

import com.chatApp.project.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ConversationRepo extends JpaRepository<Conversation, UUID> {
    List<Conversation> findByNameContainingIgnoreCase(String name);
    boolean existsByName(String name);
    boolean existsById(UUID id);
}
