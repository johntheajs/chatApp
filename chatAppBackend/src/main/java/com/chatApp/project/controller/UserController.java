package com.chatApp.project.controller;

import com.chatApp.project.model.User;
import com.chatApp.project.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User API", description = "APIs for User management")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    @Operation(summary = "List all Users", description = "Get all users and their details")
    public List<User> getAllUsers(){
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get User by ID", description = "Get user with ID in PathVariable")
    public Optional<User> getUserById(@PathVariable UUID id){
        return Optional.ofNullable(service.getUserById(id));
    }

    @PostMapping
    @Operation(summary = "Create User", description = "Add an user with object in RequestBody")
    public UUID addUser(@RequestBody User user){
        return service.addUser(user);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete the User", description = "Delete the user with ID in PathVariable")
    public String deleteUser(@PathVariable UUID id){
        try{
            service.deleteUser(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Delete User Successfully!";
    }

    @GetMapping("/search")
    @Operation(summary = "Search Users with Username", description = "Get matching users with username in RequestParam")
    public List<User> searchUsers(@RequestParam String username){
        return service.searchUsersByUsername(username);
    }

    @PutMapping
    @Operation(summary = "Update User details", description = "Update User details with object in RequestBody")
    public String updateUser(@RequestBody User user){
        try{
            service.updateUserByUsername(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "User details updated successfully for " + user.getUsername();
    }

    @PutMapping("/password")
    @Operation(summary = "Update User Password", description = "Update User password with object in RequestBody")
    public String updatePassword(@RequestBody User user){
        try{
            service.updatePasswordByUsername(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Password updated successfully for " + user.getUsername();
    }

    @GetMapping("/online/{username}")
    @Operation(summary = "Check whether User is online", description = "Check the availability of user")
    public Boolean checkIsUserOnline(@PathVariable String username){
        return service.checkUserOnline(username);
    }

    @GetMapping("/online")
    @Operation(summary = "Get all users online", description = "Get list of users available")
    public List<User> getAllUsersOnline(){
        return service.getAllUsersOnline();
    }

}
