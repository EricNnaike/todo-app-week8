package com.example.todoapp.service;

import com.example.todoapp.dto.UserDTO;
import com.example.todoapp.exceptions.UserNotFoundException;
import com.example.todoapp.model.User;

public interface UserService {
    User register(UserDTO userDTO);
    User login(String email, String password) throws UserNotFoundException;

}
