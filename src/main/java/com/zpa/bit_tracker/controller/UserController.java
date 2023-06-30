package com.zpa.bit_tracker.controller;

import com.zpa.bit_tracker.dto.UserDTO;
import com.zpa.bit_tracker.entity.User;
import com.zpa.bit_tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/getAllUsers")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getUserById/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/createUser")
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @DeleteMapping("/deleteUserById/{id}")
    public Long delete(@PathVariable Long id) {
        return userService.delete(id);
    }
}
