package com.example.genie.dto;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private int productid;
    private String productname;
    private String brand;
    private String description;
    private double price;
    private String category;
    private int quantity;

//    private String imageName;
//    private String imageType;
//    @Lob
//    private byte[] imageDate;
}
