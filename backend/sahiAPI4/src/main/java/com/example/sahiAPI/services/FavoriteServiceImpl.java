package com.example.sahiAPI.services;

import com.example.sahiAPI.entities.Car;
import com.example.sahiAPI.entities.Favorite;
import com.example.sahiAPI.entities.User;
import com.example.sahiAPI.repositories.CarRepository;
import com.example.sahiAPI.repositories.FavoriteRepository;
import com.example.sahiAPI.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteServiceImpl implements IFavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    @Override
    public Favorite addFavorite(Long userId, Long carId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Car> carOpt = carRepository.findById(carId);

        if (userOpt.isEmpty() || carOpt.isEmpty()) {
            throw new IllegalArgumentException("User or Car not found");
        }

        // Aynı favori daha önce eklenmiş mi kontrol et
        Optional<Favorite> existingFavorite = favoriteRepository.findByUserIdAndCarId(userId, carId);
        if (existingFavorite.isPresent()) {
            throw new IllegalStateException("Bu araç zaten favorilere eklenmiş.");
        }

        Favorite favorite = new Favorite();
        favorite.setUser(userOpt.get());
        favorite.setCar(carOpt.get());

        return favoriteRepository.save(favorite);
    }


    @Override
    public List<Favorite> getAllByUserId(Long userId) {
        return favoriteRepository.findByUserId(userId);
    }

    @Override
    public void deleteById(Long userId, Long carId) {
        Optional<Favorite> favoriteOpt = favoriteRepository.findByUserIdAndCarId(userId, carId);
        if (favoriteOpt.isPresent()) {
            favoriteRepository.delete(favoriteOpt.get());
        } else {
            throw new IllegalArgumentException("Belirtilen kullanıcı ve araç için favori bulunamadı.");
        }
    }


}
