package com.rupesh.User_Service.Service;

import com.rupesh.User_Service.DTOs.AllUserDTO;
import com.rupesh.User_Service.Entity.User;
import com.rupesh.User_Service.Repository.OTPRepository;
import com.rupesh.User_Service.Repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminService {

    private final UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/users")
    public List<AllUserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(allUsers -> new AllUserDTO(
                        allUsers.getName(),
                        allUsers.getPhone(),
                        allUsers.getRole(),
                        allUsers.isEnabled()))
                .toList();
    }


    @PutMapping("/{phone}/block")
    public ResponseEntity<String> blockUser(@PathVariable String phone) {
        User user = userRepository.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setEnabled(false);
        userRepository.save(user);

        return ResponseEntity.ok("User blocked");
    }


    @PutMapping("/{phone}/activate")
    public ResponseEntity<String> activateUser(@PathVariable String phone) {
        User user = userRepository.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setEnabled(true);
        userRepository.save(user);

        return ResponseEntity.ok("User activated");
    }
}
