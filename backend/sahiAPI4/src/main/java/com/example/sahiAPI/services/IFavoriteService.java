package com.example.sahiAPI.services;

import com.example.sahiAPI.entities.Favorite;

import java.util.List;

public interface IFavoriteService {
    Favorite addFavorite(Long userId, Long carId);
    public List<Favorite> getAllByUserId(Long userId);
    public void deleteById(Long userId, Long carId);

}
