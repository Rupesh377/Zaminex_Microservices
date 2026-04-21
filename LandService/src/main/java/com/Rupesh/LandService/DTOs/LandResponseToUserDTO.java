package com.Rupesh.LandService.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LandResponseToUserDTO {

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


}
