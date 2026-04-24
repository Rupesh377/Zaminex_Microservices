package com.Rupesh.LandService.Service;

import com.Rupesh.LandService.DTOs.CreateLandDTO;
import com.Rupesh.LandService.DTOs.LandDTO;
import com.Rupesh.LandService.DTOs.LandResponseToUserDTO;
import com.Rupesh.LandService.Entity.Land;
import com.Rupesh.LandService.Repository.LandRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
public class LandService {

    private final LandRepository landRepository;

    public LandService(LandRepository landRepository) {
        this.landRepository = landRepository;
    }


    public LandDTO createLand(CreateLandDTO dto, Long userId, String phone , String role) throws AccessDeniedException {
        if (!"SELLER".equals(role)) {
            throw new AccessDeniedException("Only sellers can post land");
        }
        System.out.println("USER ID = " + userId);
        Land land = Land.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .area(dto.getArea())
                .state(dto.getState())
                .city(dto.getCity())
                .locality(dto.getLocality())
                .pincode(dto.getPincode())
                .ownerId(userId)
                .contact(dto.getContact())
                .imageUrls(dto.getImageUrls())
                .active(true)
                .build();

        landRepository.save(land);
        return landToDTO(land);
    }
    public LandDTO landToDTO(Land land) {
        return new LandDTO(
                land.getId(),
                land.getTitle(),
                land.getDescription(),
                land.getPrice(),
                land.getArea(),
                land.getState(),
                land.getCity(),
                land.getLocality(),
                land.getPincode(),
                land.getContact(),
                land.getImageUrls(),
                land.isActive()
        );
    }

    public List<LandDTO> getMyLands(Long userId) {
        return landRepository.findByOwnerIdAndActiveTrue(userId)
                .stream()
                .map(this::mapToLandDTO)
                .toList();
    }
    private LandDTO mapToLandDTO(Land land) {
        return new LandDTO(
                land.getId(),
                land.getTitle(),
                land.getDescription(),
                land.getPrice(),
                land.getArea(),
                land.getState(),
                land.getCity(),
                land.getLocality(),
                land.getPincode(),
                land.getContact(),
                land.getImageUrls(),
                land.isActive()
        );
    }


    public String deleteLandById(Long landId, Long userId) throws AccessDeniedException {

        Land land = landRepository.findById(landId)
                .orElseThrow(() -> new RuntimeException("Land not found"));

        if (!land.getOwnerId().equals(userId)) {
            throw new AccessDeniedException("Not your land");
        }

        if (!land.isActive()) {
            throw new RuntimeException("Land already deleted");
        }

        land.setActive(false);
        landRepository.save(land);

        return "Land deleted successfully";
    }

    public LandDTO getLandById(Long landId, Long userId) throws AccessDeniedException {
        Land land = landRepository.findById(landId)
                .orElseThrow(() -> new RuntimeException("Land not found"));

        if (!land.getOwnerId().equals(userId)) {
            throw new AccessDeniedException("Not your land");
        }
        return mapToLandDTO(land);
    }

    public List<LandResponseToUserDTO> getAllLands() {

        return landRepository.findByActiveTrue()
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
}
