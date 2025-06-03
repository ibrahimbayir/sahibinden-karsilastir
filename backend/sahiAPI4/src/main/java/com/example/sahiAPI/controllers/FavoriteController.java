package com.example.sahiAPI.controllers;

import com.example.sahiAPI.entities.Favorite;
import com.example.sahiAPI.services.IFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@CrossOrigin("*")
public class FavoriteController {

    @Autowired
    private IFavoriteService favoriteService;

    @PostMapping
    public Favorite addFavorite(@RequestParam Long userId, @RequestParam Long carId) {
        return favoriteService.addFavorite(userId, carId);
    }

    @GetMapping
    public ResponseEntity<List<Favorite>> getAll(@RequestParam Long userId)
    {
        return ResponseEntity.ok(favoriteService.getAllByUserId(userId));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteFavorite(@RequestParam Long userId, @RequestParam Long carId) {
        try {
            favoriteService.deleteById(userId, carId);
            return ResponseEntity.ok("Favori başarıyla silindi.");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }





}
