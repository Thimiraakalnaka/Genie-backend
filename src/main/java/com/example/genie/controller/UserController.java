package com.example.genie.controller;

import com.example.genie.dto.LoginRequestDTO;
import com.example.genie.dto.UserDTO;
import com.example.genie.model.LoginRequest;
import com.example.genie.model.User;
import com.example.genie.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/adduser")
    public UserDTO saveUser(@RequestBody UserDTO userDTO){
        return userService.saveUser(userDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> newUser(@RequestBody() UserDTO userDTO){
        UserDTO newUser = userService.saveUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PutMapping("/updateuser")
    public UserDTO updateUser(@RequestBody UserDTO userDTO){
        return userService.updateUser(userDTO);
    }

    @DeleteMapping("/deleteuser")
    public String deleteUser(@RequestBody UserDTO userDTO){
        return userService.deleteUser(userDTO);
    }

    @GetMapping("/user/{userId}")
    public UserDTO getUserById(@PathVariable Integer userId){
        return userService.getUserById(userId);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequestDTO, HttpSession session) {
        try{
            boolean isAuthenticated = userService.authenticate(loginRequestDTO.getEmail(),loginRequestDTO.getPassword());

            if (isAuthenticated){
                session.setAttribute("user", loginRequestDTO.getEmail());
                return ResponseEntity.ok("Login was successful!");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unknown error occurred");
        }
    }

}
