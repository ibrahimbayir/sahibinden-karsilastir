package com.example.sahiAPI.controllers;

import com.example.sahiAPI.dtos.CarRequest;
import com.example.sahiAPI.dtos.CarResponse;
import com.example.sahiAPI.entities.Car;
import com.example.sahiAPI.services.ICarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/car")
@CrossOrigin("*")
public class CarController {

    @Autowired
    private ICarService carService;

    // Filter by brand
    @CrossOrigin
    @GetMapping("/filter/brand/{brand}")
    public ResponseEntity<List<CarResponse>> filterByBrand(@PathVariable String brand) {
        List<Car> cars = carService.filterByBrand(brand);
        return ResponseEntity.ok(cars.stream().map(CarResponse::new).collect(Collectors.toList()));
    }

    // Filter by model
    @CrossOrigin
    @GetMapping("/filter/model/{model}")
    public ResponseEntity<List<CarResponse>> filterByModel(@PathVariable String model) {
        List<Car> cars = carService.filterByModel(model);
        return ResponseEntity.ok(cars.stream().map(CarResponse::new).collect(Collectors.toList()));
    }

    // Filter by fuel type
    @CrossOrigin
    @GetMapping("/filter/fuel/{fuel}")
    public ResponseEntity<List<CarResponse>> filterByFuel(@PathVariable String fuel) {
        List<Car> cars = carService.filterByFuel(fuel);
        return ResponseEntity.ok(cars.stream().map(CarResponse::new).collect(Collectors.toList()));
    }

    // Filter by kilometers (direction: "less" or "more")
    @CrossOrigin
    @GetMapping("/filter/km/{km}/{direction}")
    public ResponseEntity<List<CarResponse>> filterByKm(@PathVariable int km, @PathVariable String direction) {
        List<Car> cars = carService.filterByKm(km, direction);
        return ResponseEntity.ok(cars.stream().map(CarResponse::new).collect(Collectors.toList()));
    }

    // Filter by price (direction: "less" or "more")
    @CrossOrigin
    @GetMapping("/filter/price/{price}/{direction}")
    public ResponseEntity<List<CarResponse>> filterByPrice(@PathVariable BigDecimal price, @PathVariable String direction) {
        List<Car> cars = carService.filterByPrice(price, direction);
        return ResponseEntity.ok(cars.stream().map(CarResponse::new).collect(Collectors.toList()));
    }



    //create a car
    @CrossOrigin
    @PostMapping
    public ResponseEntity<?> createCar(@RequestBody CarRequest request) {
        try {
            Car car = carService.createCar(request);
            return ResponseEntity.ok(new CarResponse(car));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @Valid @RequestBody Car car) {
        car.setId(id);
        Car updated = carService.updateCar(car);
        return ResponseEntity.ok(updated);
    }

    @CrossOrigin
    @DeleteMapping("{userId}/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id, @PathVariable Long userId) {

        carService.deleteCar(id, userId);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Car>> getAllByUser(@PathVariable Long userId) {
        List<Car> cars = carService.getAllByUser(userId);
        return ResponseEntity.ok(cars);
    }
    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<?> getCar(@PathVariable Long id) {
        return carService.getCarById(id)
                .map(car -> ResponseEntity.ok(new CarResponse(car)))
                .orElse(ResponseEntity.notFound().build());
    }
    @CrossOrigin
    @GetMapping("/main")
    public ResponseEntity<List<Car>> getAllForMainPage() {
        List<Car> cars = carService.getAllForMainPage();
        return ResponseEntity.ok(cars);
    }
}