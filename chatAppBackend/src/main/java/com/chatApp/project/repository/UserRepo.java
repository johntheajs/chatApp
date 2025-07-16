package com.chatApp.project.repository;

import com.chatApp.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<User, UUID>{
    List<User> findByUsernameContainingIgnoreCase(String username);
    boolean existsByUsername(String username);
    User findByUsername(String username);
    List<User> findByIsOnlineTrue();
}
