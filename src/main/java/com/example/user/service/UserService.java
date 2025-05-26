package com.example.user.service;

import com.example.user.model.CreateUserRequest;
import com.example.user.model.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(CreateUserRequest request);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(int id);
    Void deleteUserById(int id);
}



