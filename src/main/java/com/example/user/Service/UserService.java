package com.example.user.Service;

import com.example.user.model.User;
import java.util.List;

public interface UserService {

    void createUser(User user);

    List<User> getAllUsers();

    User getUserById(int id);

    void deleteUserById(int id);
}
