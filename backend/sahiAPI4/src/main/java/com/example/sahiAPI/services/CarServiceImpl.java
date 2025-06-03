package com.example.sahiAPI.services;

import com.example.sahiAPI.dtos.CarRequest;
import com.example.sahiAPI.entities.Car;
import com.example.sahiAPI.entities.CarImage;
import com.example.sahiAPI.entities.User;
import com.example.sahiAPI.repositories.CarRepository;
import com.example.sahiAPI.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements ICarService{

    @Autowired
    private CarRepository carRepo;

    @Autowired
    private UserRepository userRepo;

    @Override
    public List<Car> filterByBrand(String brand) {
        return carRepo.findAll().stream()
                .filter(car -> car.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
    }

    @Override
    public List<Car> filterByModel(String model) {
        return carRepo.findAll().stream()
                .filter(car -> car.getModel().equalsIgnoreCase(model))
                .collect(Collectors.toList());
    }

    @Override
    public List<Car> filterByFuel(String fuel) {
        return carRepo.findAll().stream()
                .filter(car -> car.getFuel().equalsIgnoreCase(fuel))
                .collect(Collectors.toList());
    }

    @Override
    public List<Car> filterByKm(int km, String direction) {
        return carRepo.findAll().stream()
                .filter(car -> {
                    if (direction.equalsIgnoreCase("less")) {
                        return car.getKm() <= km;
                    } else if (direction.equalsIgnoreCase("bigger")) {
                        return car.getKm() >= km;
                    }
                    return true;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Car> filterByPrice(BigDecimal price, String direction) {
        return carRepo.findAll().stream()
                .filter(car -> {
                    if (direction.equalsIgnoreCase("less")) {
                        return car.getPrice().compareTo(price) <= 0;
                    } else if (direction.equalsIgnoreCase("bigger")) {
                        return car.getPrice().compareTo(price) >= 0;
                    }
                    return true;
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public Car createCar(CarRequest request) {
        if (request.getImagesBase64() == null
                || request.getImagesBase64().size() < 1
                || request.getImagesBase64().size() > 3) {
            throw new IllegalArgumentException("min 1 photo");
        }

        Car car = new Car();
        car.setBrand(request.getBrand());
        car.setModel(request.getModel());
        car.setFuel(request.getFuel());
        car.setYear(request.getYear());
        car.setKm(request.getKm());
        car.setPrice(request.getPrice());
        car.setContact(request.getContact());
        car.setAvgConsumption(request.getAvgConsumption());
        car.setDescription(request.getDescription());
        User user = userRepo.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getUserId()));

        car.setUser(user);


        List<CarImage> images = request.getImagesBase64().stream()
                .map(base64 -> {
                    CarImage ci = new CarImage();
                    ci.setImageBase64(base64);
                    ci.setCar(car);
                    return ci;
                }).collect(Collectors.toList());

        car.setImages(images);

        return carRepo.save(car);
    }

    public Optional<Car> getCarById(Long id) {
        return carRepo.findById(id);
    }

      @Override
    @Transactional
    public Car updateCar(Car car) {
        // Varsayım: car.id null değil ve var olan bir kayıt
        if (!carRepo.existsById(car.getId())) {
            throw new IllegalArgumentException("Car not found with ID: " + car.getId());
        }
        Car carModel = getCarById(car.getId()).get();
        carModel.setDescription(car.getDescription());
        carModel.setModel(car.getModel());
        carModel.setAvgConsumption(car.getAvgConsumption());
        carModel.setFuel(car.getFuel());
        carModel.setKm(car.getKm());
        carModel.setBrand(car.getBrand());
        carModel.setContact(car.getContact());
        carModel.setPrice(car.getPrice());
        carModel.setYear(car.getYear());

        return carRepo.save(carModel);
    }

    @Override
    @Transactional
    public void deleteCar(Long carId, Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        // Admin kontrolü
        if (!user.isAdmin()) {
            throw new SecurityException("Unauthorized: Only admin can delete cars.");
        }

        // Car'ı bul
        Car car = carRepo.findById(carId)
                .orElseThrow(() -> new IllegalArgumentException("Car not found with ID: " + carId));

        // Silme işlemi
        carRepo.delete(car);
    }

    @Override
    @Transactional
    public List<Car> getAllByUser(Long userId) {
        return carRepo.findAllByUserId(userId);
    }

    @Override
    @Transactional
    public List<Car> getAllForMainPage() {
      

        return carRepo.findAll();
    }
}
