package com.example.sahiAPI.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CarRequest {

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

    private int avgConsumption;
    private String description;

    @NotBlank
    private String contact;

    @NotNull
    private Long userId;

    @Size(min = 1, max = 3)
    @NotNull
    private List<@NotBlank String> imagesBase64;  // Base64 encoded image strings
}
