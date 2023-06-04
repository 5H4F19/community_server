package com.community.student.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.community.student.models.User;
import com.community.student.models.UserRepo;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("")
    public List<User> getUsers(){
        return userRepo.findAll();
    }

@PostMapping("/{userId}")
public ResponseEntity<?> createUser(@PathVariable String userId, @RequestBody String password) {
    User existingUser = userRepo.findByUserId(userId);
    if (existingUser != null) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
    }

    User user = new User(userId, password);
    User savedUser = userRepo.save(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
}

@GetMapping("/{userId}")
public ResponseEntity<?> getUser(@PathVariable String userId) {
    User user = userRepo.findByUserId(userId);
    if (user != null) {
        return ResponseEntity.ok(user);
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }
}

@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody User loginRequest) {
    String userId = loginRequest.getUserId();
    String password = loginRequest.getPassword();

    User user = userRepo.findByUserId(userId);

    if (user == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    if (!password.equals(user.getPassword())) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    return ResponseEntity.ok("Login successful");
}


}
