package com.example.user.service.impl;

import com.example.user.exception.UserNotFoundException;
import com.example.user.model.CreateUserRequest;
import com.example.user.model.UserResponse;
import com.example.user.service.UserService;
import com.example.user.entity.User;
import com.example.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse createUser(CreateUserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        User savedUser = userRepository.save(user);
        return mapToUserResponse(savedUser);
    }

    private UserResponse mapToUserResponse(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail());
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToUserResponse)
                .toList();
    }

    @Override
    public UserResponse getUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            return mapToUserResponse(user.get());
        }
        else{
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public Void deleteUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
        }
        else{
            throw new UserNotFoundException("User not found");
        }
        return null;
    }
}
