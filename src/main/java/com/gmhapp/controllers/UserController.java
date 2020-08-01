package com.gmhapp.controllers;

import com.gmhapp.entities.UserEntity;
import com.gmhapp.model.AuthInfo;
import com.gmhapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/addUser")
    public UserEntity addUser(@RequestBody UserEntity userEntity) {
        return service.saveUsers(userEntity);
    }

    @PostMapping("/addUsers")
    public List<UserEntity> addUsers(@RequestBody List<UserEntity> userEntities) {
        return service.saveUsers(userEntities);
    }

    @GetMapping("/users")
    public List<UserEntity> findAllUsers() {
        return service.getUsers();
    }

    @GetMapping("/user/{id}")
    public UserEntity findUserById(@PathVariable int id) {
        return service.getUserById(id);
    }

    @GetMapping("/findUser/{userName}")
    public UserEntity findUserByName(@PathVariable String userName) {
        return service.getUserByName(userName);
    }

    @PutMapping("/updateUser")
    public UserEntity updateUser(@RequestBody UserEntity userEntity) {
        return service.updateUser(userEntity);
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable int id) {
        return service.deleteUser(id);
    }

    @PostMapping("/auth")
    public UserEntity authenticate(@RequestBody AuthInfo authInfo) {
        UserEntity user = service.getUserByName(authInfo.getUsername());
        if (user.getPassword().equals(authInfo.getPassword())) {
            return user;
        } else {
            return null;
        }
    }

}
