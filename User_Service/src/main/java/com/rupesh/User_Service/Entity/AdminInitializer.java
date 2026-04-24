package com.rupesh.User_Service.Entity;


import com.rupesh.User_Service.Enum.Role;
import com.rupesh.User_Service.Repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class AdminInitializer {

    private final UserRepository userRepository;
    public AdminInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void createDefaultAdmin() {
        String phone = "9999999999";

        boolean exists = userRepository.existsByPhone(phone);
        if (!exists) {
            User admin = User.builder()
                    .name("Default Admin")
                    .phone(phone)
                    .role(Role.ADMIN)
                    .enabled(true)
                    .build();
            userRepository.save(admin);
            System.out.println("Default admin created: phone = 9999999999");
        }
    }
}

