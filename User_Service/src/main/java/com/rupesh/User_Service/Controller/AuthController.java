package com.rupesh.User_Service.Controller;

import com.rupesh.User_Service.DTOs.JwtResponseDTO;
import com.rupesh.User_Service.DTOs.RequestOtp;
import com.rupesh.User_Service.DTOs.VerifyOTPDTO;
import com.rupesh.User_Service.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/requestOTP")
    public ResponseEntity<String> requestOtp(@RequestBody RequestOtp requestOtp)
    {
        return ResponseEntity.ok(userService.requestOtp(requestOtp));
    }

    @PostMapping("/verifyOTP")
    public ResponseEntity<JwtResponseDTO> verify(@RequestBody VerifyOTPDTO verifyOTPDTO)
    {
        return ResponseEntity.ok(userService.verifyOTP(verifyOTPDTO));
    }
}
