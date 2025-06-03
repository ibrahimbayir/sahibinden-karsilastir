package com.example.sahiAPI.services;

import com.example.sahiAPI.dtos.CarRequest;
import com.example.sahiAPI.entities.Car;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ICarService {
    public Car createCar(CarRequest carRequest);
    public Car updateCar(Car car);
    public void deleteCar(Long carId, Long userId);
    public List<Car> getAllByUser(Long userId);
    public List<Car> getAllForMainPage();

    Optional<Car> getCarById(Long id);
    List<Car> filterByBrand(String brand);
    List<Car> filterByModel(String model);
    List<Car> filterByFuel(String fuel);
    List<Car> filterByKm(int km, String direction);
    List<Car> filterByPrice(BigDecimal price, String direction);
}
