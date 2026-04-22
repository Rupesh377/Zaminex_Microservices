package com.Rupesh.LandService.DTOs;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LandDTO {

    private Long id;

    private String title;
    private String description;
    private Double price;
    private Double area;

    private String state;
    private String city;
    private String locality;
    private String pincode;
    private String contact;
    private List<String> imageUrls;

    private boolean active=true;

}
