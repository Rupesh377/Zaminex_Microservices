package com.Rupesh.LandService.Config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(Map.of(
                "cloud_name", "ddoipwsew",
                "api_key", "845652632725894",
                "api_secret", "MkjmK4mdFMu0PeFMCVTkKhL5xGw"));
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
