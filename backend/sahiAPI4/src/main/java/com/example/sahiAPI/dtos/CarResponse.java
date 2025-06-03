package com.example.sahiAPI.dtos;

import com.example.sahiAPI.entities.Car;
import com.example.sahiAPI.entities.CarImage;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CarResponse {
    private Long id;

    @NotBlank
    private String brand;

    @NotBlank
    private String model;

    @NotBlank
    private String fuel;

    @Min(1900)
    private int year;

    @Min(0)
    private int km;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal price;

    @NotBlank
    private String contact;

    @NotNull
    private Long userId;

    @Size(min = 1, max = 3)
    @NotNull
    private List<@NotBlank String> imagesBase64;
    private int avgConsumption;
    private String description;// Base64 encoded image strings



    public CarResponse(Car car) {
        this.imagesBase64 = car.getImages().stream()
                .map(CarImage::getImageBase64)
                .collect(Collectors.toList());
        this.userId = car.getUser().getId();
        this.contact = car.getContact();
        this.price = car.getPrice();
        this.km = car.getKm();
        this.year = car.getYear();
        this.fuel = car.getFuel();
        this.model = car.getModel();
        this.brand = car.getBrand();
        this.id = car.getId();
        this.avgConsumption = car.getAvgConsumption();
        this.description = car.getDescription();

    }
}
