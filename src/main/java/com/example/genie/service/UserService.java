package com.example.genie.service;

import com.example.genie.dto.UserDTO;
import com.example.genie.model.User;
import com.example.genie.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public List<UserDTO> getAllUsers(){
        List<User> userList=userRepo.findAll();

        return modelMapper.map(userList,new TypeToken<List<UserDTO>>(){}.getType());
    }

    public UserDTO saveUser(UserDTO userDTO){
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        userRepo.save(modelMapper.map(userDTO,User.class));
        return userDTO;
//        // Hash the password and set it in both the DTO and Entity
//        String hashedPassword = bCryptPasswordEncoder.encode(userDTO.getPassword());
//        userDTO.setPasswordHash(hashedPassword); // Set hashed password in DTO
//
//        // Map DTO to Entity
//        User user = modelMapper.map(userDTO, User.class);
//        user.setPasswordHash(hashedPassword); // Ensure it's set in the entity too
//
//        // Save the user entity
//        userRepo.save(user);
//
//        return userDTO;
    }

    public UserDTO updateUser(UserDTO userDTO){
        userRepo.save(modelMapper.map(userDTO,User.class));
        return userDTO;
    }

    public String deleteUser(UserDTO userDTO){
        userRepo.delete(modelMapper.map(userDTO,User.class));
        return "user deleted successfully";
    }

    public UserDTO getUserById(Integer userId){
        User user=userRepo.getUserById(userId);
        return modelMapper.map(user,UserDTO.class);
    }

    public boolean authenticate(String email, String password) {
        User user = userRepo.findByEmail(email);

        if(!user.getEmail().equals(email)){
            throw new UsernameNotFoundException("User does not exist in the database");
        }

        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("The password is incorrect");
        }

        return  true;
    }
}
