package com.zpa.bit_tracker.controller;

import com.zpa.bit_tracker.entity.User;
import com.zpa.bit_tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/greeting")
public class HomePageController {

    @Autowired
    private UserService userService;
    @PostMapping("/createUser")
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/homepage")
    public ResponseEntity<String> homepage() throws IOException {
        ClassPathResource resource = new ClassPathResource("static/HomePage.html");
        byte[] fileContent = new byte[(int) resource.getFile().length()];
        resource.getInputStream().read(fileContent);

        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(new String(fileContent, StandardCharsets.UTF_8));
    }

    @GetMapping("/registration")
    public ResponseEntity<String> registration() throws IOException {
        ClassPathResource resource = new ClassPathResource("static/RegistrationPage.html");
        byte[] fileContent = new byte[(int) resource.getFile().length()];
        resource.getInputStream().read(fileContent);

        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(new String(fileContent, StandardCharsets.UTF_8));
    }

    @GetMapping("/login")
    public ResponseEntity<String> login() throws IOException {
        ClassPathResource resource = new ClassPathResource("static/LoginPage.html");
        byte[] fileContent = new byte[(int) resource.getFile().length()];
        resource.getInputStream().read(fileContent);

        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(new String(fileContent, StandardCharsets.UTF_8));
    }

    @GetMapping("/showUserInfoPage")
    public ResponseEntity<String> showUserInfoPage() throws IOException {
        ClassPathResource resource = new ClassPathResource("static/ShowUserInfoPage.html");
        byte[] fileContent = new byte[(int) resource.getFile().length()];
        resource.getInputStream().read(fileContent);

        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(new String(fileContent, StandardCharsets.UTF_8));
    }
}
