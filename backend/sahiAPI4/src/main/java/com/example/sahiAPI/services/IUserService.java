package com.example.sahiAPI.services;

import com.example.sahiAPI.dtos.UserDto;
import com.example.sahiAPI.entities.User;

import java.util.List;

public interface IUserService {
    public User signUp(User user);
    public User login(String email, String password);
    User adminLogin(String email, String password);
    public List<UserDto> getAll(Long userId);
    public void deleteUser(Long userId, Long id);
    public User Update(User user);

}

