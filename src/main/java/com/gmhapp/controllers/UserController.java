package com.gmhapp.controllers;

import com.gmhapp.entities.UserEntity;
import com.gmhapp.enums.ValidationQuestions;
import com.gmhapp.exception.ApiException;
import com.gmhapp.model.AuthInfo;
import com.gmhapp.model.ForgotPassInfo;
import com.gmhapp.model.MailInfo;
import com.gmhapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
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
        try {
            return service.saveUsers(userEntity);
        } catch (ApiException e) {
            e.printStackTrace();
            throw e;
        }

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
        try {
            return service.getUserById(id);
        } catch (ApiException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/findUser/{userName}")
    public UserEntity findUserByName(@PathVariable String userName) {
        try {
            return service.getUserByName(userName);
        } catch (ApiException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @PutMapping("/updateUser")
    public UserEntity updateUser(@RequestBody UserEntity userEntity) {
        try {
            return service.updateUser(userEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable int id) {
        try {
            return service.deleteUser(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @PostMapping("/auth")
    public UserEntity authenticate(@RequestBody AuthInfo authInfo) {
        UserEntity user = service.getUserByName(authInfo.getUsername());
        if (user.getPassword().equals(authInfo.getPassword())) {
            return user;
        } else {
            throw new ApiException("אחד מהפרטים שהזנת אינם נכונים", HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping("/contactUs/{id}")
    public void contactUs(@PathVariable int id,
                          @RequestBody MailInfo mailInfo){
        UserEntity userEntity = service.getUserById(id);
        try {
            service.sendEmail(mailInfo ,userEntity);
        }
        catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @RequestMapping("/forgotPass/{username}")
    public void forgotPassword(@PathVariable String username,
                               @RequestBody ForgotPassInfo info){
        UserEntity userEntity = service.getUserByName(username);
        try {
            service.validAnswer(info ,userEntity);
        }
        catch (ApiException e){
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/questions")
    public List<String> getAllQuestions(){
        return service.getAllValidationQuestions();
    }

    @GetMapping("/questions/{username}")
    public String getQuestionByID(@PathVariable String username){
        return '\"' + service.getUserByName(username).getValidationQuestion().getHebrew() + '\"';
    }

    @GetMapping("/totalUsers")
    public int howMuchUsers(){
        return service.getTotalUsers();
    }






}
