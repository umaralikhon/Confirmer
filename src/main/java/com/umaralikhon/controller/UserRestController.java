package com.umaralikhon.controller;

import com.umaralikhon.email.EmailService;
import com.umaralikhon.entity.User;
import com.umaralikhon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
public class UserRestController {
    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/users")
    public List<User> getAllUser() {
        List<User> allUser = userService.getAllUser();
        return allUser;
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user, BindingResult binding) {
        if (binding.hasErrors()) {
            return new ResponseEntity<User>(user, HttpStatus.BAD_REQUEST);
        }

        emailService.sendEmail(user.getEmail(), user.getStCode());
        User savedUser = userService.saveUser(user);

        return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("/confirm")
    public String confirmUser(@RequestBody User user) throws Exception {
//        List<User> allUsers = userService.getAllUser();
        User foundedUser = userService.findByEmailAndStCode(user.getEmail(), user.getStCode());
//        User foundedUser = userService.findByStCode(user.getStCode());


//        if (binding.hasErrors()) {
//            return binding.getNestedPath() + "[]" + binding.getObjectName();
//        }

//        for (User u : allUsers) {
//            if (u.getStCode().equals(user.getStCode()) && (u.getEmail().equals(user.getEmail()))) {
//                u.setConfirmed(true);
//                u.setStCode(null);
//                userService.saveUser(u);
//                return "Your account confirmed!";
//            }
//        }

        if(foundedUser != null){
            foundedUser.setConfirmed(true);
            foundedUser.setStCode(null);
            userService.saveUser(foundedUser);
            return  "Your account confirmed!";
        }
        return "Invalid code!";
    }
}
