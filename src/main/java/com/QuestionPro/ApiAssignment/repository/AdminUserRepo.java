package com.QuestionPro.ApiAssignment.repository;

import com.QuestionPro.ApiAssignment.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminUserRepo extends JpaRepository<Users, String> {
    Users findByUsername(String username);
}
