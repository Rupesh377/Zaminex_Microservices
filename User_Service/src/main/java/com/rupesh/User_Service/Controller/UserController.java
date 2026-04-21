package com.rupesh.User_Service.Controller;

import com.rupesh.User_Service.DTOs.SignUpDTO;
import com.rupesh.User_Service.DTOs.SignUpResponseDTO;
import com.rupesh.User_Service.DTOs.UpdateNameDTO;
import com.rupesh.User_Service.Entity.User;
import com.rupesh.User_Service.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (User) auth.getPrincipal();
    }

    @PostMapping("/create")
    public ResponseEntity<SignUpResponseDTO> create(@RequestBody SignUpDTO signUpDTO)
    {
        return ResponseEntity.ok(userService.createUser(signUpDTO));
    }

    @PutMapping("/change")
    public ResponseEntity<String> change(Authentication authentication)
    {
        User user=(User) authentication.getPrincipal();
        return ResponseEntity.ok(userService.changeRole(user.getPhone()));
    }

    @GetMapping("/me")
    public ResponseEntity<SignUpResponseDTO> profile(Authentication authentication)
    {
        User user=(User) authentication.getPrincipal();
        return ResponseEntity.ok(userService.getProfile(user.getPhone()));
    }

    @PutMapping("/me")
    public ResponseEntity<SignUpResponseDTO> ChangeName(@RequestBody UpdateNameDTO updateNameDTO, Authentication authentication)
    {
        User user=(User) authentication.getPrincipal();
        return ResponseEntity.ok(userService.ChangeName(updateNameDTO , user.getPhone()));
    }
}