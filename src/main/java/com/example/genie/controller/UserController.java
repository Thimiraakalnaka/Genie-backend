package com.example.genie.controller;

import com.example.genie.dto.UserDTO;
import com.example.genie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value="api/v1/")

public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getusers")
    public List<UserDTO> getUsers(){
        return userService.getAllUsers();
    }
}
