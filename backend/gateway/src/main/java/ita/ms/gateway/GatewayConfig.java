package ita.ms.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("users", r -> r.path("/api/users/**")
                .uri("http://localhost:8080"))
                .route("properties", r -> r.path("/api/properties/**")
                        .uri("http://localhost:8081"))
                .route("properties", r -> r.path("/api/properties/**")
                        .uri("http://localhost:8082"))
                .build();
    }
}