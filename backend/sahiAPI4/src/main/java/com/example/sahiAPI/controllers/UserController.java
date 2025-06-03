package com.example.sahiAPI.controllers;

import com.example.sahiAPI.dtos.UserDto;
import com.example.sahiAPI.entities.User;
import com.example.sahiAPI.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @PostMapping("/update")
    public ResponseEntity<User> update(@RequestBody User user){
        return ResponseEntity.ok(userService.Update(user));
    }

    @DeleteMapping("/{userId}/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId, @PathVariable Long id){
        userService.deleteUser(userId, id);
        return ResponseEntity.ok("Successful attempt");
    }

    @GetMapping("/getall/{userId}")
    public ResponseEntity<List<UserDto>> getAll(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getAll(userId));
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody User user) {
        User created = userService.signUp(user);
        return ResponseEntity.ok(created);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestParam String email, @RequestParam String password) {
        User loggedIn = userService.login(email, password);
        return ResponseEntity.ok(loggedIn);
    }

    @PostMapping("/admin-login")
    public ResponseEntity<?> adminLogin(@RequestParam String email, @RequestParam String password) {
        User adminUser = userService.adminLogin(email, password);
        if (adminUser != null) {
            return ResponseEntity.ok(adminUser);
        } else {
            return ResponseEntity.status(401).body("Not an admin or invalid credentials");
        }
    }
}
