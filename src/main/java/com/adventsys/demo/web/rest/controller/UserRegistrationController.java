package com.adventsys.demo.web.rest.controller;

import com.adventsys.demo.model.User;
import com.adventsys.demo.model.UserLogin;
import com.adventsys.demo.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserRegistrationController {

    private final UserRepository userRepository;

    public UserRegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/getuser", produces = {MediaType.APPLICATION_JSON_VALUE},consumes = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@RequestBody UserLogin userLogin) {

        Optional<User> user = userRepository.findById(userLogin.getEmailid());
        if (StringUtils.isBlank(userLogin.getEmailid()) || StringUtils.isBlank(userLogin.getPassword())) return null;
        if (user.isPresent() && StringUtils.equals(userLogin.getPassword(), user.get().getPassword())) return user.get();//TODO issue authentication cookie in this case.
        return null;//TODO throw illegal argument exception for invalid username or password
    }

    @PostMapping(value = "/registeruser", produces = {MediaType.APPLICATION_JSON_VALUE},consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean registerUser(@RequestBody User user) {
        if (user != null && validateUser(user)) {
            userRepository.save(user);//TODO validate if the user already exists and put a time out in case db takes
            // more than desired time
            return true;
        }
        return false;
    }

    private boolean validateUser(User user) {
        //TODO add necessary validations
        return true;
    }

    @GetMapping(value = "/getallusers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll(){
        List<User> result = new ArrayList<>();
        Iterable<User> userIterator = userRepository.findAll();
        userIterator.forEach(user -> result.add(user));
        return result;
    }

    //API for the admin to update a user details
    @PostMapping(value = "/updateuser", produces = {MediaType.APPLICATION_JSON_VALUE},consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean updateUser(@RequestBody User user, User loggedInUser) {
        if (user != loggedInUser || !loggedInUser.isIsadmin()) return false;
        //Check the permissions of the users logged if he has admin permission only then allow this operation.
        if (user != null && validateUser(user)) {
            userRepository.deleteById(user.getEmailid());
            userRepository.save(user);
            return true;
        }
        return false;
    }

}
