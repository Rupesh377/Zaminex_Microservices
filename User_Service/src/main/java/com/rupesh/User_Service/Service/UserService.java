package com.rupesh.User_Service.Service;
import com.rupesh.User_Service.DTOs.*;
import com.rupesh.User_Service.Entity.OTP;
import com.rupesh.User_Service.Entity.User;
import com.rupesh.User_Service.Enum.Role;
import com.rupesh.User_Service.ExceptionalHandling.ResourceNotFound;
import com.rupesh.User_Service.Repository.OTPRepository;
import com.rupesh.User_Service.Repository.UserRepository;
import com.rupesh.User_Service.Security.JwtUtil;
import jakarta.transaction.Transactional;
import org.jspecify.annotations.Nullable;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final OTPRepository otpRepository;

    public UserService(UserRepository userRepository, JwtUtil jwtUtil, OTPRepository otpRepository) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.otpRepository = otpRepository;
    }

    @Transactional
    public SignUpResponseDTO createUser(SignUpDTO signUpDTO) {

        if(userRepository.existsByPhone(signUpDTO.getPhone()))
        {
            throw new RuntimeException("User Already Exists with this number");
        }
        User user=User.builder()
                .name(signUpDTO.getName())
                .phone(signUpDTO.getPhone())
                .role(Role.BUYER)
                .enabled(true)
                .build();
        userRepository.save(user);
        return SignUpResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .phone(user.getPhone())
                .role(user.getRole())
                .build();
    }

    @Transactional
    public String requestOtp(RequestOtp requestOtp) {

        User user =userRepository.findByPhone(requestOtp.getPhone()).orElseThrow(()
                -> new ResourceNotFound("User not found "));

        if(!user.isEnabled())
        {
            throw new AccessDeniedException("User is Blocked");
        }
        String otp=String.valueOf(1000 + new Random().nextInt(9000));

        otpRepository.deleteByPhone(requestOtp.getPhone());

        otpRepository.save(OTP.builder()
                .phone(user.getPhone())
                .code(otp)
                .expiry(LocalDateTime.now().plusMinutes(5))
                .build());
        return "Your OTP is: "+otp;
    }


    @Transactional
    public  JwtResponseDTO verifyOTP(VerifyOTPDTO verifyOTPDTO) {

        User user = userRepository.findByPhone(verifyOTPDTO.getPhone())
                .orElseThrow(() -> new ResourceNotFound("User not found"));

        OTP otp= otpRepository.findByPhone(verifyOTPDTO.getPhone()).orElseThrow(()->
                new RuntimeException("OTP Not Found"));

        if(otp.getExpiry().isBefore(LocalDateTime.now()))
        {
            throw new RuntimeException("OTP Expired");
        }
        if(!otp.getCode().equals(verifyOTPDTO.getCode()))
        {
            throw new RuntimeException("Invalid OTP");
        }
        if (!user.isEnabled())
            throw  new AccessDeniedException("Yor are blocked");

        String token = jwtUtil.generateToken(user);

        return new JwtResponseDTO(user.getId() , token);
    }


    public String changeRole(String phone) {
        User user=userRepository.findByPhone(phone).orElseThrow(()-> new ResourceNotFound("User Not Found"));
        if(!user.isEnabled())
            throw new AccessDeniedException("User is Blocked");

        if(user.getRole().equals(Role.SELLER))
            return "Already a Seller";

        if(user.getRole() == Role.ADMIN)
            return "Admin can't change its role";

        user.setRole(Role.SELLER);
        userRepository.save(user);
        return "Role changed to "+user.getRole();
    }


    public SignUpResponseDTO getProfile(String phone) {
        User user=userRepository.findByPhone(phone).orElseThrow(()-> new RuntimeException("User Not Found"));
        if(!user.isEnabled())
            throw new AccessDeniedException("User is Blocked");

        return new SignUpResponseDTO(user.getId() , user.getPhone() , user.getName() , user.getRole());
    }



    public SignUpResponseDTO ChangeName(UpdateNameDTO name, String phone) {

        User user = userRepository.findByPhone(phone)
                .orElseThrow(() -> new ResourceNotFound("User not found"));

        user.setName(name.getName());
        userRepository.save(user);
        return new SignUpResponseDTO(user.getId(),  user.getPhone(),user.getName(), user.getRole());
    }

}
