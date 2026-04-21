package com.Rupesh.LandService.Controller;

import com.Rupesh.LandService.Config.JwtUtil;
import com.Rupesh.LandService.DTOs.LandDTO;
import com.Rupesh.LandService.DTOs.LandResponseToUserDTO;
import com.Rupesh.LandService.Entity.Land;
import com.Rupesh.LandService.Repository.LandRepository;
import org.apache.catalina.Role;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/lands/admin")
public class AdminController {


    private final LandRepository landRepository;
    private final JwtUtil jwtUtil;

    public AdminController(LandRepository landRepository, JwtUtil jwtUtil) {
        this.landRepository = landRepository;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/get")
    public List<LandResponseToUserDTO> getAllLands() {
        return landRepository.findAll()
                .stream().map(land -> new LandResponseToUserDTO(
                        land.getTitle(),
                        land.getDescription(),
                        land.getPrice(),
                        land.getArea(),
                        land.getState(),
                        land.getCity(),
                        land.getLocality(),
                        land.getPincode(),
                        land.getContact(),
                        land.getImageUrls()
                        )).toList();
    }

    @PutMapping("/{id}/deactivate")
    public String deactivateLand(@PathVariable Long id , @RequestHeader("Authorization") String token) throws AccessDeniedException {

        String role = jwtUtil.extractRole(token);

        if (!"ADMIN".equals(role)) {
            throw new AccessDeniedException("Only ADMIN can deactivate land");
        }
        Land land = landRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Land not found"));

        land.setActive(false);
        landRepository.save(land);

        return "Land deactivated";
    }
}
