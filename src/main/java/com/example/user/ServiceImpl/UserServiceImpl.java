package com.example.user.ServiceImpl;

import com.example.user.Service.UserService;
import com.example.user.model.User;
import com.example.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int id) {
       Optional<User> optionalUser=userRepository.findById(id);
       return optionalUser.orElse(null);
    }

    @Override
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }
}
