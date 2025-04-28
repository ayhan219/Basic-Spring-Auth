package com.springauth.springboot_app.controller;

import com.springauth.springboot_app.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AuthController {
    private List<User> users = new ArrayList<>();
    private BCryptPasswordEncoder passwordEncoder;

    public AuthController(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> signup(@RequestBody User user) {
        if (user.getUsername() == null || user.getPassword() == null) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Username and Password must not be null");
            return ResponseEntity.badRequest().body(errorResponse);
        }
        String hashedPW = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPW);
        System.out.println("hashed PW" + hashedPW);
        System.out.println("after hashed PW" + user);

        users.add(user);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Signup successful!");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody User user) {
        System.out.println(user);
        if (user.getUsername() == null || user.getPassword() == null) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "username and pasword must not be null");
            return ResponseEntity.badRequest().build();
        }

        for (User registeredUser : users) {
            if (registeredUser.getUsername().equals(user.getUsername())
                    && registeredUser.getPassword().equals(user.getPassword())) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "successfully logined!");
                return ResponseEntity.ok(response);
            }
        }
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Invalid username or password");
        return ResponseEntity.status(401).body(errorResponse);
    }

    @PostMapping("/changepassword")
    public ResponseEntity<Map<String, String>> changePassword(@RequestBody User request) {
        System.out.println("request user" + request.getPassword());
        for (User specificUser : users) {
            if (specificUser.getUsername().equals(request.getUsername())) {
                specificUser.setPassword(request.getPassword());
                Map<String, String> response = new HashMap<>();
                response.put("message", "Password changed successfully");
                System.out.println("after changed password" + users);
                return ResponseEntity.ok(response);
            }
        }
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Username not found");
        return ResponseEntity.status(404).body(errorResponse);
    }

}
