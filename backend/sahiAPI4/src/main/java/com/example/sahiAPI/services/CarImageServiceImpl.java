package com.example.sahiAPI.services;

import com.example.sahiAPI.entities.CarImage;
import com.example.sahiAPI.repositories.CarImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarImageServiceImpl implements ICarImageService{

    @Autowired
    CarImageRepository carImageRepo;

    @Override
    public CarImage create(CarImage image) {
        return carImageRepo.save(image);
    }

    @Override
    public List<CarImage> viewAll() {
        return (List<CarImage>)carImageRepo.findAll();
    }

    @Override
    public CarImage viewById(long id) {
        return carImageRepo.findById(id).get();
    }
}
