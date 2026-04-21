package com.Rupesh.LandService.Controller;

import com.Rupesh.LandService.Config.JwtUtil;
import com.Rupesh.LandService.DTOs.LandDTO;
import com.Rupesh.LandService.DTOs.LandResponseToUserDTO;
import com.Rupesh.LandService.Service.LandService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {

    private final LandService landService;

    public PublicController(LandService landService) {
        this.landService = landService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<LandResponseToUserDTO>> getAllLand()
    {
        return ResponseEntity.ok(landService.getAllLands());
    }
}
