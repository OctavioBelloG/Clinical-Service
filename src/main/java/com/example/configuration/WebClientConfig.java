package com.example.configuration; // Ajusta a tu paquete

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    @LoadBalanced // ¡Esto es magia! Permite usar el nombre "users-service" en lugar de "localhost:8081"
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}