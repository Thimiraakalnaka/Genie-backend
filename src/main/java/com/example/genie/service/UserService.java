package com.example.genie.service;

import com.example.genie.dto.UserDTO;
import com.example.genie.model.User;
import com.example.genie.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<UserDTO> getAllUsers(){
        List<User> userList=userRepo.findAll();

        return modelMapper.map(userList,new TypeToken<List<UserDTO>>(){}.getType());
    }

    public UserDTO saveUser(UserDTO userDTO){
        userRepo.save(modelMapper.map(userDTO,User.class));
        return userDTO;
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
}
