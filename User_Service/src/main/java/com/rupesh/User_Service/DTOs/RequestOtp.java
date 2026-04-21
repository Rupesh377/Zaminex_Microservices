package com.rupesh.User_Service.DTOs;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestOtp {


    @NotNull(message = "Phone is required")
    @Min(value = 1000000000L, message = "Invalid phone number")
    @Max(value = 999999999999L, message = "Invalid phone number")
    private String phone;
}
