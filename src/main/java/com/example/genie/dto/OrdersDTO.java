package com.example.genie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDTO {
    private int orderid;
    private int quantity;
    private double price;
    private int productid;
    private int id;
}
