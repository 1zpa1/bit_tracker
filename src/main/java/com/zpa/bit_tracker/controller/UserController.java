package com.zpa.bit_tracker.controller;

import com.zpa.bit_tracker.dto.UserDTO;
import com.zpa.bit_tracker.entity.User;
import com.zpa.bit_tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getUserById/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/deleteUserById/{id}")
    public Long delete(@PathVariable Long id) {
        return userService.delete(id);
    }

    @GetMapping("/showUserInfo")
    public UserDTO showUserInfo(Principal principal) {
        return userService.showUserInfo(principal);
    }

}
