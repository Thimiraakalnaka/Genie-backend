package com.example.genie.controller;

import com.example.genie.dto.OrdersDTO;
import com.example.genie.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/")
public class OrdersController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/getorders")
    public List<OrdersDTO> getOrders(){
        return orderService.getAllOrders();
    }

    @PostMapping("/addorder")
    public OrdersDTO saveOrder(@RequestBody OrdersDTO ordersDTO){
        return orderService.saveOrder(ordersDTO);
    }
}
