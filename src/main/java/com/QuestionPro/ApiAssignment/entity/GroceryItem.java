package com.QuestionPro.ApiAssignment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "groceryitems")
public class GroceryItem {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "price")
    private int price;
    @Column(name = "inventory")
    private int inventory;
    @Column(name = "name")
    private String name;
}

