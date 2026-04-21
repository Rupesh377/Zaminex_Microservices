package com.rupesh.User_Service.DTOs;

import com.rupesh.User_Service.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpResponseDTO {

    private Long id;
    private String phone;
    private String name;
    private Role role;
}
