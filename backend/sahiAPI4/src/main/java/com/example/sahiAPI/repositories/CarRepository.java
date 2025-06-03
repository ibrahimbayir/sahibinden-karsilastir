package com.example.sahiAPI.repositories;

import com.example.sahiAPI.entities.Car;
import com.example.sahiAPI.entities.Comparison;
import com.example.sahiAPI.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAllByUserId(Long userId);

    List<Car> findByYearLessThanEqual(int year);

    List<Car> findByYearLessThan(int year);

}
