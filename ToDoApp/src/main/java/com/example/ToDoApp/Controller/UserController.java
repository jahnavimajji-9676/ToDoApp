package com.example.ToDoApp.Controller;

import com.example.ToDoApp.Models.UserModel;
import com.example.ToDoApp.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> signUp(@RequestBody UserModel userModel) {
        // Validate phone number: must start with '+' followed by exactly 2 digits (country code)
        // and then a 10-digit number starting with 6,7,8, or 9.
        String phoneNumber = userModel.getPhoneNumber();
        if (phoneNumber == null || !phoneNumber.matches("^\\+\\d{2}[6789]\\d{9}$")) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Invalid phone number. It must have a '+' followed by exactly 2 digits for the country code and a 10-digit number starting with 6, 7, 8, or 9.");
            return ResponseEntity.badRequest().body(errorResponse);
        }

        // Validate email using regex: basic check ensuring an '@' and proper domain structure.
        String email = userModel.getEmail();
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Invalid email address.");
            return ResponseEntity.badRequest().body(errorResponse);
        }

        // Pre-check: See if a user already exists with the given email.
        List<UserModel> existingUsers = userRepository.findByEmail(email);
        if (!existingUsers.isEmpty()) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "User already exists with this email.");
            return ResponseEntity.badRequest().body(errorResponse);
        }

        UserModel savedUser = userRepository.save(userModel);
        Map<String, String> response = new HashMap<>();
        response.put("Status", "User signed up successfully!");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signin")
    public ResponseEntity<Map<String, String>> signIn(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");
        List<UserModel> users = userRepository.validateUser(email, password);
        Map<String, String> response = new HashMap<>();
        if (!users.isEmpty()) {
            response.put("Status", "User validated");
            response.put("userId", String.valueOf(users.get(0).getId()));
            response.put("name", users.get(0).getName());
        } else {
            response.put("Status", "Invalid credentials");
        }
        return ResponseEntity.ok(response);
    }
}
