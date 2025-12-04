package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChatbotInteractionRequest {

    @NotNull(message = "El ID del paciente es obligatorio")
    private Long patientId;

    @NotBlank(message = "Los síntomas reportados son obligatorios")
    private String reportedSymptoms;

    private String recommendation;
}