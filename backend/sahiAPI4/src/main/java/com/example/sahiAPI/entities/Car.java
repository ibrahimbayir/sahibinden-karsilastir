package com.example.sahiAPI.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "cars")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "brand is required")
    private String brand;
    @NotBlank(message = "model is required")
    private String model;
    @NotBlank(message = "fuel is required")
    private String fuel;
    @Min(value = 1900, message = "Year must be greater than 1900")
    private int year;
    @Min(value = 0, message = "Kilometers must be non-negative")
    private int km;
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be positive")
    private BigDecimal price;
    @NotBlank(message = "contact is required")
    private String contact;

    private int avgConsumption;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    @Size(min = 1, max = 3, message = "You must provide between 1 and 3 images")
    @Valid
    private List<CarImage> images;
}

