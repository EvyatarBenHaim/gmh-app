package com.gmhapp.services;

import com.gmhapp.entities.UserEntity;
import com.gmhapp.enums.ValidationQuestions;
import com.gmhapp.exception.ApiException;
import com.gmhapp.model.ForgotPassInfo;
import com.gmhapp.model.MailInfo;
import com.gmhapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

@Service
public class UserService {

    private final static Logger logger = Logger.getLogger(UserService.class.getName());
    private final UserRepository repository;
    private final JavaMailSender javaMailSender;

    @Autowired
    public UserService(UserRepository repository, JavaMailSender javaMailSender) {
        this.repository = repository;
        this.javaMailSender = javaMailSender;
    }

    public UserEntity saveUsers(UserEntity userEntity) {
        if (repository.existsUserEntityByUserName(userEntity.getUserName())) {
            logger.error("The username " + userEntity.getUserName() + " already " +
                    "exists in the system!");
            throw new ApiException("The username " + userEntity.getUserName() +
                    " already exists!. Try another one.", HttpStatus.CONFLICT);
        } else if (repository.existsUserEntityByEmail(userEntity.getEmail())) {
            logger.error("The email address " + userEntity.getEmail() + " already " +
                    "exists in the system !");
            throw new ApiException("The email address " + userEntity.getEmail() +
                    " already exists!. Try another one.", HttpStatus.CONFLICT);
        }
        logger.debug("UserName " + userEntity.getUserName() + " successfully added.");
        return repository.save(userEntity);

    }

    public List<UserEntity> saveUsers(List<UserEntity> userEntities) {
        return repository.saveAll(userEntities);
    }

    public List<UserEntity> getUsers() {
        return repository.findAll();
    }

    public UserEntity getUserById(int id) {
        UserEntity userEntity = repository.findById(id).orElse(null);
        if (Objects.isNull(userEntity))
            throw new ApiException("User not found! the id is: " + id, HttpStatus.NOT_FOUND);
        return userEntity;
    }

    public UserEntity getUserByName(String userName) {
        if (repository.existsUserEntityByUserName(userName))
            return repository.findByUserName(userName);

        logger.error("The username " + userName + " not found!");
        throw new ApiException("The username " + userName +
                " not found!", HttpStatus.NOT_FOUND);
    }

    public String deleteUser(int id) {
        repository.deleteById(id);
        logger.debug("User ID: " + id + "removed successfully.");
        return "User ID:" + id + " removed successfully.";
    }

    public void sendEmail(MailInfo mailInfo, UserEntity user) throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("gmhapplication@gmail.com");
        //mail.setFrom(user.getEmail());
        mail.setSubject(mailInfo.getSubject());
        mail.setText("message from- " + user.getfName() + " " + user.getlName() +
                " about " + mailInfo.getSubject() + ":" + "\n" +
                mailInfo.getText());

        javaMailSender.send(mail);
        logger.info("email sent successfully.");

    }

    public void updatePassword(UserEntity userEntity, String newPass) {
        userEntity.setPassword(newPass);
        repository.save(userEntity);
        logger.info("The password of " + userEntity.getUserName() +
                " changed successfully.");
    }

    public void validAnswer(ForgotPassInfo forgotPassInfo, UserEntity user) {
        String ques = user.getValidationQuestion().toString();
        String ans = user.getValidationAnswer();

        if (forgotPassInfo.getAnswer().equals(ans))
            updatePassword(user, forgotPassInfo.getNewPass());

        else
            throw new ApiException("wrong answer or question!", HttpStatus.CONFLICT);

    }

    public List<String> getAllValidationQuestions() {
        List<String> validationQuestions = Arrays.stream(ValidationQuestions.values()).map(ValidationQuestions::getHebrew).collect(Collectors.toList());
        logger.info("Get validationQuestions successfully: " + validationQuestions);
        return validationQuestions;
    }

    public UserEntity updateUser(UserEntity userEntity) {
        UserEntity existingUserEntity = repository.findById(userEntity.getId()).orElse(null);
        existingUserEntity.setUserName(userEntity.getfName());
        existingUserEntity.setPassword(userEntity.getPassword());
        existingUserEntity.setfName(userEntity.getfName());
        existingUserEntity.setlName(userEntity.getlName());
        existingUserEntity.setAddress(userEntity.getAddress());
        existingUserEntity.setPhoneNum(userEntity.getPhoneNum());
        existingUserEntity.setEmail(userEntity.getEmail());
        existingUserEntity.getProductList().addAll(userEntity.getProductList());
        logger.debug("User id " + userEntity.getId() + " successfully updated.");
        return repository.save(existingUserEntity);
    }


}
