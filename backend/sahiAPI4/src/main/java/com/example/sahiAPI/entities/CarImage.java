package com.example.sahiAPI.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;


import java.sql.Blob;
import java.util.Date;

@Entity
@Table(name = "car_images")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Lob // big string
    private String imageBase64;

    @ManyToOne
    @JoinColumn(name = "car_id")
    @JsonBackReference
    private Car car;
}
