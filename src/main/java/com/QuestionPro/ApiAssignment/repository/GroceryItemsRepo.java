package com.QuestionPro.ApiAssignment.repository;

import com.QuestionPro.ApiAssignment.entity.GroceryItem;
import com.QuestionPro.ApiAssignment.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroceryItemsRepo extends JpaRepository<GroceryItem, String> {
}

