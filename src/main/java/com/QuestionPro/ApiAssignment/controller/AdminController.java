package com.QuestionPro.ApiAssignment.controller;

import com.QuestionPro.ApiAssignment.entity.GroceryItem;
import com.QuestionPro.ApiAssignment.payload.*;
import com.QuestionPro.ApiAssignment.repository.AdminUserRepo;
import com.QuestionPro.ApiAssignment.repository.GroceryItemsRepo;
import com.QuestionPro.ApiAssignment.validation.UsersValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/adminOperations")
@Slf4j
public class AdminController {
    @Autowired
    private UsersValidation usersValidation;
    @Autowired
    private AdminUserRepo adminUserRepo;

    @Autowired
    private GroceryItemsRepo groceryItemsRepo;

    @PostMapping("/addNewItem")
    public ResponseEntity<String> addNewItem(@Valid @RequestBody NewItemPayload newItemPayload){
        try {
            usersValidation.isValid(newItemPayload.getUsername(), newItemPayload.getPassword(), "admin");
        } catch (Exception e) {
            log.info("Login validation failed.");
            return ResponseEntity.ok("Login validation failed!");
        }
        if(groceryItemsRepo.existsById(newItemPayload.getItemId())){
            log.error("Item Id already exists");
            return ResponseEntity.ok("Item Id already exists");
        }
        GroceryItem groceryItem = new GroceryItem();
        groceryItem.setId(newItemPayload.getItemId());
        groceryItem.setName(newItemPayload.getItemName());
        groceryItem.setInventory(newItemPayload.getInventory());
        groceryItem.setPrice(newItemPayload.getPrice());
        groceryItemsRepo.save(groceryItem);
        return ResponseEntity.ok("Successfully added the new item!");
    }

    @PostMapping("/viewExistingItems")
    public ResponseEntity<String> viewExistingItems(@Valid @RequestBody RegistrationPayload registrationPayload){
        try {
            usersValidation.isValid(registrationPayload.getUsername(), registrationPayload.getPassword(), "admin");
        } catch (Exception e) {
            log.info("Login validation failed.");
            return ResponseEntity.ok("Login validation failed!");
        }
        List<GroceryItem> allItems = groceryItemsRepo.findAll();
        return ResponseEntity.ok(allItems.toString());
    }

    @DeleteMapping("/removeItem")
    public ResponseEntity<String> removeItem(@Valid @RequestBody RemoveItemPayload removeItemPayload){
        try {
            usersValidation.isValid(removeItemPayload.getUsername(), removeItemPayload.getPassword(), "admin");
        } catch (Exception e) {
            log.info("Login validation failed.");
            return ResponseEntity.ok("Login validation failed!");
        }
        String response = "";
        try{
            groceryItemsRepo.deleteById(removeItemPayload.getItemId());
            response = "Deleted the item successfully!";
        }
        catch (Exception e){
            response = "Item Id does not exist!";
            log.info(e.getMessage());
        }
        log.info(response);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updatePrice")
    public ResponseEntity<String> updatePrice(@Valid @RequestBody UpdatePricePayload updatePricePayload){
        try {
            usersValidation.isValid(updatePricePayload.getUsername(), updatePricePayload.getPassword(), "admin");
        } catch (Exception e) {
            log.info("Login validation failed.");
            return ResponseEntity.ok("Login validation failed!");
        }
        String response;
        try{
            GroceryItem groceryItem = groceryItemsRepo.getReferenceById(updatePricePayload.getItemId());
            groceryItem.setPrice(updatePricePayload.getNewPrice());
            groceryItemsRepo.save(groceryItem);
            response = "Updated the price of the item successfully!";
        }
        catch (Exception e){
            response = "Item Id does not exist!";
            log.info(e.getMessage());
        }
        log.info(response);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/updateName")
    public ResponseEntity<String> updateName(@Valid @RequestBody UpdateNamePayload updateNamePayload){
        try {
            usersValidation.isValid(updateNamePayload.getUsername(), updateNamePayload.getPassword(), "admin");
        } catch (Exception e) {
            log.info("Login validation failed.");
            return ResponseEntity.ok("Login validation failed!");
        }
        String response = "";
        try{
            GroceryItem groceryItem = groceryItemsRepo.getReferenceById(updateNamePayload.getItemId());
            groceryItem.setName(updateNamePayload.getNewName());
            groceryItemsRepo.save(groceryItem);
            response = "Updated the name of the item successfully!";
        }
        catch (Exception e){
            response = "Item Id does not exist!";
            log.info(e.getMessage());
        }
        log.info(response);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/manageInventory")
    public ResponseEntity<String> manageInventory(@Valid @RequestBody ManageInventoryPayload manageInventoryPayload){
        try {
            usersValidation.isValid(manageInventoryPayload.getUsername(), manageInventoryPayload.getPassword(), "admin");
        } catch (Exception e) {
            log.info("Login validation failed.");
            return ResponseEntity.ok("Login validation failed!");
        }
        String response = "";
        try{
            GroceryItem groceryItem = groceryItemsRepo.getReferenceById(manageInventoryPayload.getItemId());
            groceryItem.setInventory(manageInventoryPayload.getNewInventory());
            groceryItemsRepo.save(groceryItem);
            response = "Updated the inventory of the item successfully!";
        }
        catch (Exception e){
            response = "Item Id does not exist!";
            log.info(e.getMessage());
        }
        log.info(response);
        return ResponseEntity.ok(response);
    }
}
