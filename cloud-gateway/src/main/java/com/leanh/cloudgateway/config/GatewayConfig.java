package com.leanh.cloudgateway.config;

import com.leanh.cloudgateway.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes().route("user", r -> r.path("/users/**").filters(f -> f.filter(filter)).uri("lb://user"))
                .route("product", r -> r.path("/product/**").filters(f -> f.filter(filter)).uri("lb://product")).build();
    }

}
