package com.example.sahiAPI.controllers;

import com.example.sahiAPI.dtos.ComparisonCarDto;
import com.example.sahiAPI.entities.Comparison;
import com.example.sahiAPI.services.IComparisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/comparison")
public class ComparisonController {

    @Autowired
    private IComparisonService comparisonService;
    @CrossOrigin
    @GetMapping("/{userId}")
    public ResponseEntity<?> getComparisonByUserId(@PathVariable Long userId) {
        try {
            ComparisonCarDto dto = comparisonService.getComparisonCarsByUserId(userId);
            return ResponseEntity.ok(dto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("User not found");
        }
    }
    @CrossOrigin
    @PostMapping("/{userId}/{car1Id}/{car2Id}")
    public ResponseEntity<Comparison> createOrUpdateComparison(
            @PathVariable Long userId,
            @PathVariable Long car1Id,
            @PathVariable Long car2Id) {

        Comparison comparison = comparisonService.createOrUpdateComparison(userId, car1Id, car2Id);
        return ResponseEntity.ok(comparison);
    }

}
