package com.chatApp.project.service;

import com.chatApp.project.exception.UsernameAlreadyExistsException;
import com.chatApp.project.model.User;
import com.chatApp.project.repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    public List<User> getAllUsers(){
        return repo.findAll();
    }

    public User getUserById(UUID id){
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
    }

    public UUID addUser(User user){

        //Check if username already exists
        if(repo.existsByUsername(user.getUsername())){
            throw new UsernameAlreadyExistsException(user.getUsername());
        }

        // Hash the password
        String hashed = BCrypt.hashpw(user.getHashed_password(), BCrypt.gensalt());

        //Create Calendar and get last_seen and created_at
        Calendar now = Calendar.getInstance();
        if(user.getCreated_at() == null){
            user.setCreated_at(now);
        }
        if(user.getLast_seen() == null){
            user.setLast_seen(now);
        }

        user.setHashed_password(hashed);

        return repo.save(user).getId();

    }

    public void deleteUser(UUID id){
        repo.deleteById(id);
    }

    public List<User> searchUsersByUsername(String username){
        return repo.findByUsernameContainingIgnoreCase(username);
    }

    public void updateUserByUsername(User updatedUser){
        User user = repo.findByUsername(updatedUser.getUsername());

        if (user == null) {
            throw new EntityNotFoundException("User with username " + updatedUser.getUsername() + " not found");
        }

        user.setEmail(updatedUser.getEmail());
        user.setAvatar_url(updatedUser.getAvatar_url());
        user.setOnline(updatedUser.getOnline());
        user.setLast_seen(user.getLast_seen());

        repo.save(user);
    }

    public void updatePasswordByUsername(User updatedUser){
        User user = repo.findByUsername(updatedUser.getUsername());

        if (user == null) {
            throw new EntityNotFoundException("User with username " + updatedUser.getUsername() + " not found");
        }

        String hashed = BCrypt.hashpw(updatedUser.getHashed_password(), BCrypt.gensalt());

        user.setHashed_password(hashed);
        repo.save(user);
    }

    public Boolean checkUserOnline(String username){
        User user = repo.findByUsername(username);

        if (user == null) {
            throw new EntityNotFoundException("User with username " + username + " not found");
        }

        return user.getOnline();
    }

    public List<User> getAllUsersOnline(){
        return repo.findByIsOnlineTrue();
    }


}
