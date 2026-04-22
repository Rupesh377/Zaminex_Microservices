package com.rupesh.API.Gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("User_Service", r -> r
                        .path("/auth/**","/user/**","/admin/users/**")
                        .uri("http://localhost:8081"))

                .route("Land_Service", r -> r
                        .path("/lands/**","/admin/lands/**","/public/**")
                        .uri("http://localhost:8082"))


                .build();
    }
}
