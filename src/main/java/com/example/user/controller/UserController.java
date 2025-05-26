package com.example.user.controller;

import com.example.user.Service.UserService;
import com.example.user.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger LOGGER= LogManager.getLogger(UserController.class);
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping (value="/add", method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody User user) {
        userService.createUser(user);
        return new ResponseEntity<>("User created", HttpStatus.CREATED);

    }
    @RequestMapping(value="/all", method=RequestMethod.GET)
    public ResponseEntity<List<User>> getUsers() {
        List<User> users= userService.getAllUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable int id) {
        User user=userService.getUserById(id);
        if(user==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
    }

    @RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        User user=userService.getUserById(id);
        if(user!=null) {
            userService.deleteUserById(id);
            return new ResponseEntity<>("User deleted", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("User Not Found",HttpStatus.NOT_FOUND);
        }

    }
}
