package com.example.service;

import com.example.dto.PatientDTO;
import com.example.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ExternalUserService {

    private final WebClient.Builder webClientBuilder;

    // Llama a users-service para obtener un paciente
    public PatientDTO getPatientById(Long patientId) {
        return webClientBuilder.build()
                .get()
               .uri("http://users-service/api/v1/patients/" + patientId) // Asume que users-service tiene este endpoint
                .retrieve()
                .bodyToMono(PatientDTO.class)
                .onErrorResume(e -> Mono.empty()) // Si falla, retorna vacío
                .block(); // Bloqueamos para simular síncrono (simple para este proyecto)
    }

    // Verifica si existe un usuario (para doctores/reportes)
    public boolean existsUserById(Long userId) {
        try {
            UserDTO user = webClientBuilder.build()
                    .get()
                   .uri("http://users-service/api/v1/users/" + userId)
                    .retrieve()
                    .bodyToMono(UserDTO.class)
                    .block();
            return user != null;
        } catch (Exception e) {
            return false;
        }
    }

    // Verifica si existe un paciente
    public boolean existsPatientById(Long patientId) {
        return getPatientById(patientId) != null;
    }
}