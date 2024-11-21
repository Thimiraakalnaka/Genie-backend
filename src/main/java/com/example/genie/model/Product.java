package com.example.genie.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productid;
    private String productname;
    private String brand;
    private String description;
    private double price;
    private String category;
    private int quantity;

    private String imageName;
    private String imageType;
    @Lob
    private byte[] imageDate;
}
