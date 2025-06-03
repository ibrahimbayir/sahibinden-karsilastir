package com.example.sahiAPI.controllers;

import com.example.sahiAPI.dtos.CarImageReadDto;
import com.example.sahiAPI.entities.CarImage;
import com.example.sahiAPI.services.CarImageServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.sql.rowset.serial.SerialException;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/carImage")
public class CarImageController {
    @Autowired
    CarImageServiceImpl carImageService;

//    @PostMapping
//    public String addImagePost(HttpServletRequest request, @RequestParam("image")MultipartFile file)
//        throws IOException, SerialException, SQLException{
//        byte[] bytes = file.getBytes();
//        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
//
//        CarImage carImage = new CarImage();
//        carImage.setImage(blob);
//        carImageService.create(carImage);
//        return "redirect:/";
//    }

    @GetMapping
    public ModelAndView  getAll()
    {
        ModelAndView mv = new ModelAndView("index");
        List<CarImage> carImageList = carImageService.viewAll();
        mv.addObject("carImageList", carImageList); // listeyi view'a gönder
        return mv;
    }

//    @CrossOrigin(origins = "*")
//    @GetMapping("/get")
//    public ResponseEntity<?> getFirst() throws SQLException, IOException {
//        List<CarImage> carImageList = carImageService.viewAll();
//
//        return carImageList.stream().findFirst()
//                .map(carImage -> {
//                    try {
//                        return ResponseEntity.ok(new CarImageReadDto(carImage));
//                    } catch (Exception e) {
//                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing image");
//                    }
//                })
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
}
