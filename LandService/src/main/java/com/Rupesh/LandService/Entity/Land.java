package com.Rupesh.LandService.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Land {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 1000)
    private String description;

    private Double price;
    private Double area;

    private String state;
    private String city;
    private String locality;
    private String pincode;

    @Column(nullable = false)
    private Long ownerId;

    private String contact;

    @ElementCollection
    @CollectionTable(name = "land_images", joinColumns = @JoinColumn(name = "land_id"))
    @Column(name = "image_url")
    private List<String> imageUrls;

    private boolean active = true;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
