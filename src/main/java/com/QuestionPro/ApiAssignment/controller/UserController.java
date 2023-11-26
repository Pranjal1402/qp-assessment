package com.QuestionPro.ApiAssignment.controller;

import com.QuestionPro.ApiAssignment.entity.GroceryItem;
import com.QuestionPro.ApiAssignment.payload.OrderPayload;
import com.QuestionPro.ApiAssignment.payload.RegistrationPayload;
import com.QuestionPro.ApiAssignment.repository.GroceryItemsRepo;
import com.QuestionPro.ApiAssignment.validation.UsersValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("")
@Slf4j
public class UserController {
    @Autowired
    private UsersValidation usersValidation;
    @Autowired
    private GroceryItemsRepo groceryItemsRepo;
    @PostMapping("/viewExistingItems")
    public ResponseEntity<String> viewExistingItems(@Valid @RequestBody RegistrationPayload registrationPayload){
        try {
            usersValidation.isValid(registrationPayload.getUsername(), registrationPayload.getPassword(), "User");
        } catch (Exception e) {
            log.info("Login validation failed.");
            return ResponseEntity.ok("Login validation failed!");
        }
        List<GroceryItem> allItems = groceryItemsRepo.findAll();
        allItems.removeIf(item -> item.getInventory() == 0);
        return ResponseEntity.ok(allItems.toString());
    }

    @PostMapping("/order")
    public ResponseEntity<String> order(@Valid @RequestBody OrderPayload orderPayload){
        try {
            usersValidation.isValid(orderPayload.getUsername(), orderPayload.getPassword(), "User");
        } catch (Exception e) {
            log.info("Login validation failed.");
            return ResponseEntity.ok("Login validation failed!");
        }
        Map<String , Integer> orders = orderPayload.getOrders();
        int cost = 0;
        Map<String, Integer> finalOrders = new HashMap<>();
        for(Map.Entry<String, Integer> entry : orders.entrySet()){
            String orderId = entry.getKey();
            int quantity = entry.getValue();
            if(!groceryItemsRepo.existsById(orderId)){
                finalOrders.put(null, 0);
                continue;
            }
            GroceryItem groceryItem = groceryItemsRepo.getReferenceById(orderId);
            int minVal = Math.min(groceryItem.getInventory(), quantity);
            groceryItem.setInventory(groceryItem.getInventory() - minVal);
            groceryItemsRepo.save(groceryItem);
            cost += minVal * groceryItem.getPrice();
            finalOrders.put(groceryItem.getName(), minVal);
        }
        return ResponseEntity.ok("The cost is : "+cost+". The quantities of each of the items are listed as below : \n" + finalOrders);
    }
}
