package com.Rupesh.LandService.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserClient {

    private final RestTemplate restTemplate;

    public UserClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getUserName(Long userId) {
        String url = "http://localhost:8081/users/" + userId;
        return restTemplate.getForObject(url, String.class);
    }
}
