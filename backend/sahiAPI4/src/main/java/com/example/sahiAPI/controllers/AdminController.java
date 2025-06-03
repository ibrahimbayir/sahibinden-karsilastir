package com.example.sahiAPI.controllers;

import com.example.sahiAPI.entities.Admin;
import com.example.sahiAPI.services.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<Admin> login(@RequestParam String username, @RequestParam String password) {
        Admin admin = adminService.login(username, password);
        if (admin != null) {
            return ResponseEntity.ok(admin);
        } else {
            return ResponseEntity.status(401).build(); // Unauthorized
        }
    }

    @DeleteMapping("/delete-car/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable("id") Long id) {
        adminService.deleteCar(id);
        return ResponseEntity.ok().build();
    }
}