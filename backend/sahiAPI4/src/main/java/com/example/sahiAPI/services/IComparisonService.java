package com.example.sahiAPI.services;

import com.example.sahiAPI.dtos.ComparisonCarDto;
import com.example.sahiAPI.entities.Comparison;
import org.springframework.stereotype.Service;


public interface IComparisonService {
    Comparison createOrUpdateComparison(Long userId, Long car1Id, Long car2Id);
    ComparisonCarDto getComparisonCarsByUserId(Long userId);
}
