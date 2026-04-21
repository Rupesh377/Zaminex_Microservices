package com.rupesh.User_Service.Entity;

import com.rupesh.User_Service.Enum.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false , unique = true)
    private String phone;

    @Column(nullable = false , unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean enabled=true;
}
