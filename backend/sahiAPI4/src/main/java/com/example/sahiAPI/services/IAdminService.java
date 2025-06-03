package com.example.sahiAPI.services;

import com.example.sahiAPI.entities.Admin;

public interface IAdminService {
    public Admin login(String username, String password);
    public void deleteCar(Long carId);
}
