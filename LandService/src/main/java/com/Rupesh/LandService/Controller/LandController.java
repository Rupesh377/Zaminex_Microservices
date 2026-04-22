package com.Rupesh.LandService.Controller;

import com.Rupesh.LandService.DTOs.CreateLandDTO;
import com.Rupesh.LandService.DTOs.LandDTO;
import com.Rupesh.LandService.Config.JwtUtil;
import com.Rupesh.LandService.Service.LandService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/lands")
public class LandController {

    private final LandService landService;
    private final JwtUtil jwtUtil;

    public LandController(LandService landService, JwtUtil jwtUtil) {
        this.landService = landService;
        this.jwtUtil = jwtUtil;
    }

    @PreAuthorize("hasRole('SELLER')")
    @PutMapping("/create")
    public ResponseEntity<LandDTO> createLand(@RequestBody CreateLandDTO landDTO ,
                                              @RequestHeader("Authorization") String token) throws AccessDeniedException {
        Long userId = jwtUtil.extractUserId(token);
        String phone = jwtUtil.extractPhone(token);
        String role = jwtUtil.extractRole(token);
        return ResponseEntity.ok(landService.createLand(landDTO, userId, phone ,role));
    }

    @PreAuthorize("hasRole('SELLER')")
    @GetMapping("/myLands")
    public ResponseEntity<List<LandDTO>> getLand(@RequestHeader("Authorization") String token)
    {
        Long userId= jwtUtil.extractUserId(token);
        return ResponseEntity.ok(landService.getMyLands(userId));
    }

    @PreAuthorize("hasRole('SELLER')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id, @RequestHeader("Authorization") String token) throws AccessDeniedException {
        Long userId= jwtUtil.extractUserId(token);
        return ResponseEntity.ok(landService.deleteLandById(id , userId));
    }

    @PreAuthorize("hasRole('SELLER')")
    @GetMapping("/get/{id}")
    public ResponseEntity<LandDTO> getById(@PathVariable Long id ,@RequestHeader("Authorization") String token) throws AccessDeniedException {
        Long userId= jwtUtil.extractUserId(token);
        return ResponseEntity.ok(landService.getLandById(id , userId));
    }
}
