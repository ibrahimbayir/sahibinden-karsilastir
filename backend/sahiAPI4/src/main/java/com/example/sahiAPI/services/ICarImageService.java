package com.example.sahiAPI.services;

import com.example.sahiAPI.entities.CarImage;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public interface ICarImageService {
    public CarImage create(CarImage image);
    public List<CarImage> viewAll();
    public CarImage viewById(long id);
}
