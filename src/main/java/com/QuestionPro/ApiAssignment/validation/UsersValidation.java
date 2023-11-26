package com.QuestionPro.ApiAssignment.validation;

import com.QuestionPro.ApiAssignment.entity.Users;
import com.QuestionPro.ApiAssignment.repository.AdminUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springfox.documentation.annotations.ApiIgnore;

@Component
public class UsersValidation {
    @Autowired
    private AdminUserRepo adminUserRepo;
    public void isValid(String username, String password, String role) throws Exception{
        //check from database
        Users user = adminUserRepo.findByUsername(username);
        if(user == null){
            throw new Exception("User not found in the database.");
        }
        if(!user.getRole().equalsIgnoreCase(role) || !user.getPassword().equals(password)){
            throw new Exception("Either role or password is wrong.");
        }
    }
}
