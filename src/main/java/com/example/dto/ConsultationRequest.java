package com.example.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ConsultationRequest {

    @NotNull(message = "El ID de la cita es obligatorio")
    private Long appointmentId;

    private String diagnosis;

    private String treatment;

    private String observations;
}