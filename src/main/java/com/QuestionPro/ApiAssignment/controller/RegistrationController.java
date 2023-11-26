package com.QuestionPro.ApiAssignment.controller;

import com.QuestionPro.ApiAssignment.entity.Users;
import com.QuestionPro.ApiAssignment.payload.RegistrationPayload;
import com.QuestionPro.ApiAssignment.repository.AdminUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private AdminUserRepo adminUserRepo;

    @PostMapping("/admin")
    public ResponseEntity<String> registrationForAdmin(@Valid @RequestBody RegistrationPayload registrationPayload){
        Users user = new Users();
        user.setPassword(registrationPayload.getPassword());
        user.setRole("Admin");
        user.setUsername(registrationPayload.getUsername());
        adminUserRepo.save(user);
        return ResponseEntity.ok("Admin Successfully registered!");
    }

    @PostMapping("/user")
    public ResponseEntity<String> registrationForUser(@Valid @RequestBody RegistrationPayload registrationPayload){
        Users user = new Users();
        user.setPassword(registrationPayload.getPassword());
        user.setRole("User");
        user.setUsername(registrationPayload.getUsername());
        adminUserRepo.save(user);
        return ResponseEntity.ok("User Successfully registered!");
    }
}
