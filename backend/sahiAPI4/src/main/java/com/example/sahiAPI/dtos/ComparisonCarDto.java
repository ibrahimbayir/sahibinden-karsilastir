package com.example.sahiAPI.dtos;

import com.example.sahiAPI.entities.Car;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ComparisonCarDto {
    private Car car1;
    private Car car2;
}
