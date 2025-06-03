package com.example.sahiAPI.dtos;

import com.example.sahiAPI.entities.CarImage;
import lombok.Data;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

@Data
public class CarImageReadDto {
    private Long id;
    private String imageBase64;

    // Constructor
//    public CarImageReadDto(CarImage carImage) throws SQLException, IOException {
//        this.id = carImage.getId();
//        Blob blob = carImage.getImage();
//        if (blob != null) {
//            byte[] bytes = blob.getBytes(1, (int) blob.length());
//            this.imageBase64 = Base64.getEncoder().encodeToString(bytes);
//        }
//    }
}
