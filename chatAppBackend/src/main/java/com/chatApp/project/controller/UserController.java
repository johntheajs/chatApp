package com.chatApp.project.controller;

import com.chatApp.project.model.User;
import com.chatApp.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public List<User> getAllUsers(){
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable UUID id){
        return Optional.ofNullable(service.getUserById(id));
    }

    @PostMapping
    public UUID addUser(@RequestBody User user){
        return service.addUser(user);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable UUID id){
        try{
            service.deleteUser(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Delete User Successfully!";
    }

    @GetMapping("/search")
    public List<User> searchUsers(@RequestParam String username){
        return service.searchUsersByUsername(username);
    }

    @PutMapping
    public String updateUser(@RequestBody User user){
        try{
            service.updateUserByUsername(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "User details updated successfully for " + user.getUsername();
    }

    @PutMapping("/password")
    public String updatePassword(@RequestBody User user){
        try{
            service.updatePasswordByUsername(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Password updated successfully for " + user.getUsername();
    }

    @GetMapping("/online/{username}")
    public Boolean checkIsUserOnline(@PathVariable String username){
        return service.checkUserOnline(username);
    }

    @GetMapping("/online")
    public List<User> getAllUsersOnline(){
        return service.getAllUsersOnline();
    }

}
