package com.example.sahiAPI.services;

import com.example.sahiAPI.dtos.UserDto;
import com.example.sahiAPI.entities.User;
import com.example.sahiAPI.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public User signUp(User user) {
        // email check
        if (userRepo.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }
        return userRepo.save(user);
    }

    @Override
    public User login(String email, String password) {
        Optional<User> userOpt = userRepo.findByEmail(email);
        if (userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {
            return userOpt.get();
        } else {
            throw new IllegalArgumentException("Invalid email or password");
        }
    }

    @Override
    public User adminLogin(String email, String password) {
        User user = userRepo.findByEmailAndPassword(email, password);
        if (user != null && user.isAdmin()) {
            return user;
        }
        return null;
    }

    @Override
    public List<UserDto> getAll(Long adminId) {
        List<User> users = userRepo.findAll(); // veya filtreli getirme varsa
        return users.stream()
                .map(user -> new UserDto(user.getId(),user.getEmail(), user.getPassword(), user.getUsername()))
                .collect(Collectors.toList());
    }


    @Override
    public void deleteUser(Long adminUserId, Long targetUserId) {
        // Admin olup olmadığını kontrol et
        User adminUser = userRepo.findById(adminUserId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + adminUserId));

        if (!adminUser.isAdmin()) {
            throw new SecurityException("Unauthorized: Only admin can delete users.");
        }

        // Silinecek kullanıcıyı bul
        User targetUser = userRepo.findById(targetUserId)
                .orElseThrow(() -> new IllegalArgumentException("Target user not found with ID: " + targetUserId));

        // Silme işlemi
        userRepo.delete(targetUser);
    }


    @Override
    public User Update(User user) {
        User targetUser = userRepo.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Target user not found with ID: " + user.getId()));

        targetUser.setUsername(user.getUsername());
        targetUser.setEmail(user.getEmail());
        targetUser.setPassword(user.getPassword());
        // Eğer admin gibi alanlar varsa onları da ekle:
        // targetUser.setAdmin(user.getAdmin());

         userRepo.save(targetUser);
         return targetUser;
    }

}





