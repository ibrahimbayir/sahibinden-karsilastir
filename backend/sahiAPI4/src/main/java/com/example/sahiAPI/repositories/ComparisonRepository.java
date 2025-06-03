package com.example.sahiAPI.repositories;

import com.example.sahiAPI.entities.Comparison;
import com.example.sahiAPI.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComparisonRepository extends JpaRepository<Comparison, Long> {
    Optional<Comparison> findByUser(User user);
}
