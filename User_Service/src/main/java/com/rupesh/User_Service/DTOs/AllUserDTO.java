package com.rupesh.User_Service.DTOs;

import com.rupesh.User_Service.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AllUserDTO {

    private String name;
    private String phone;
    private Role role;
    private boolean enabled;

}
