package com.example.sahiAPI.services;

import com.example.sahiAPI.dtos.ComparisonCarDto;
import com.example.sahiAPI.entities.Car;
import com.example.sahiAPI.entities.Comparison;
import com.example.sahiAPI.entities.User;
import com.example.sahiAPI.repositories.CarRepository;
import com.example.sahiAPI.repositories.ComparisonRepository;
import com.example.sahiAPI.repositories.UserRepository;
import com.example.sahiAPI.services.IComparisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ComparisonServiceImpl implements IComparisonService {

    @Autowired
    private ComparisonRepository comparisonRepo;
    @Autowired
    private  UserRepository userRepo;
    @Autowired
    private  CarRepository carRepo;


    @Override
    @Transactional(readOnly = true)
    public ComparisonCarDto getComparisonCarsByUserId(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Comparison comparison = comparisonRepo.findByUser(user)
                .orElseThrow(() -> new NoSuchElementException("Comparison not found for user"));

        return new ComparisonCarDto(comparison.getCar1(), comparison.getCar2());
    }

    @Override
    @Transactional
    public Comparison createOrUpdateComparison(Long userId, Long car1Id, Long car2Id) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Car car1 = carRepo.findById(car1Id)
                .orElseThrow(() -> new IllegalArgumentException("Car 1 not found"));
        Car car2 = carRepo.findById(car2Id)
                .orElseThrow(() -> new IllegalArgumentException("Car 2 not found"));

        Comparison comparison = comparisonRepo.findByUser(user)
                .orElseGet(() -> {
                    Comparison newComparison = new Comparison();
                    newComparison.setUser(user);
                    return newComparison;
                });

        comparison.setCar1(car1);
        comparison.setCar2(car2);

        return comparisonRepo.save(comparison);
    }
}
