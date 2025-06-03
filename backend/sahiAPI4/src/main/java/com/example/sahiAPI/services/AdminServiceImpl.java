package com.example.sahiAPI.services;

import com.example.sahiAPI.entities.Admin;
import com.example.sahiAPI.repositories.AdminRepository;
import com.example.sahiAPI.repositories.CarRepository;
import com.example.sahiAPI.services.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private CarRepository carRepository;

    @Override
    public Admin login(String username, String password) {
        List<Admin> admins = adminRepository.findAll();

        for (Admin admin : admins) {
            if (username.equals(admin.getUsername()) && password.equals(admin.getPassword()))
            {
                return admin;
            }
        }

        return null;
    }

    @Override
    public void deleteCar(Long carId) {
        carRepository.deleteById(carId);
    }
}
